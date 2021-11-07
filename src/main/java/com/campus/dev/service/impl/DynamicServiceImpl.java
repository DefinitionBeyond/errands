package com.campus.dev.service.impl;

import com.campus.dev.bean.BadException;
import com.campus.dev.bean.BizException;
import com.campus.dev.bean.TransactionalForAll;
import com.campus.dev.dao.mapper.*;
import com.campus.dev.rest.dynamic.request.*;
import com.campus.dev.rest.dynamic.response.DynamicCommentDTO;
import com.campus.dev.rest.dynamic.response.DynamicInfoDTO;
import com.campus.dev.model.*;
import com.campus.dev.service.DynamicService;
import com.campus.dev.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DynamicServiceImpl implements DynamicService {


    @Autowired
    private LabelService labelService;

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    DynamicMapper dynamicMapper;

    @Autowired
    DynamicInfoMapper dynamicInfoMapper;

    @Autowired
    DynamicLikeMapper dynamicLikeMapper;

    @Autowired
    DynamicCommentMapper dynamicCommentMapper;

    @Autowired
    UserMapper userMapper;

    @TransactionalForAll
    @Override
    public void create(DynamicCreateDTO dynamicDTO, boolean isPublish) {

        List<Long> labels = labelMapper.listIdByName(dynamicDTO.getLabels());

        DynamicDO dynamicDO = DynamicDO.builder()
                .content(dynamicDTO.getContent())
                .labels(labels)
                .publishStatus(isPublish? true:false)
                .build();

        dynamicMapper.insert(dynamicDO);

        if(!CollectionUtils.isEmpty(dynamicDTO.getPictureIds())) {
            List<DynamicInfoDO> dynamicInfoDOS = new ArrayList<>();
            dynamicDTO.getPictureIds().forEach((v)->{
                dynamicInfoDOS.add(DynamicInfoDO.builder()
                        .dynamicId(dynamicDO.getId())
                        .infoPicture(v)
                        .build()
                );
            });
            dynamicInfoMapper.bulkInsert(dynamicInfoDOS);
        }
    }

    @Override
    public List<DynamicInfoDTO> list(DynamicListDTO request) {
        DynamicSearchDTO searchDTO = new DynamicSearchDTO();
        if(StringUtils.hasText(request.getLabel())){
            List<Long> labels = labelService.listIdByName(Arrays.asList(request.getLabel()));

            if(!CollectionUtils.isEmpty(labels)){
                searchDTO.setLabel(labels.get(0));
            }
        }
        searchDTO.setOrder(request.getOrder());
        searchDTO.setPosition(request.getPosition());
        searchDTO.setStatus(2);
        searchDTO.setPublishStatus(1);
        searchDTO.setUid(request.getUid());
        List<DynamicDO> dynamicDOList = dynamicMapper.list(searchDTO);
        Set<Long> uids = dynamicDOList.stream().map(DynamicDO::getUid).collect(Collectors.toSet());
        Map<Long, UserDO> userMap = userMapper.listByIds(uids.stream().collect(Collectors.toList())).stream().collect(Collectors.toMap(UserDO::getId, Function.identity(), (v1, v2) -> v1));

        List<Long> dynamicIds = dynamicDOList.stream().map(DynamicDO::getId).collect(Collectors.toList());
        List<DynamicLikeDO> dynamicLikeDOList = dynamicLikeMapper.getByDynamicIds(dynamicIds);

        List<DynamicInfoDO> dynamicInfoDOList = dynamicInfoMapper.getByDynamicIds(dynamicIds);

        Set<Long> labelIds = new HashSet<>();
        dynamicDOList.stream().map(it->labelIds.addAll(it.getLabels()));

        Map<Long, LabelDO> labelMap = labelService.listByIds(labelIds.stream().collect(Collectors.toList())).stream().collect(Collectors.toMap(LabelDO::getId, Function.identity(), (k1, k2) -> k1));

        List<DynamicInfoDTO> result = new LinkedList<>();
        dynamicDOList.forEach((dynamicDO)->{
            DynamicInfoDTO response = DynamicInfoDTO.builder()
                    .user(userMap.get(dynamicDO.getUid()))
                    .isLike(dynamicLikeDOList.stream().filter( it->it.getUid() == dynamicDO.getUid() && it.getDynamicId() == dynamicDO.getId()).collect(Collectors.toList()).isEmpty()? false:true)
                    .labels(getSomeData(labelMap, dynamicDO.getLabels()).stream().map(LabelDO::getLabelName).collect(Collectors.toList()))
                    .picList(dynamicInfoDOList.stream().filter(it->it.getDynamicId()==dynamicDO.getId()).map(DynamicInfoDO::getInfoPicture).collect(Collectors.toList()))
                    .dynamic(dynamicDO)
                    .build();
            result.add(response);
        });

        return result;
    }

    private <T,K> Set<K> getSomeData(Map<T, K> map, List<T> keys){
        return keys.stream().map(it->map.get(it)).collect(Collectors.toSet());
    }

    @TransactionalForAll
    @Override
    public void delete(long dynamicId) {
        dynamicInfoMapper.deleteByDynamicId(dynamicId);
        dynamicLikeMapper.deleteByDynamicId(dynamicId);
        dynamicCommentMapper.deleteByDynamicId(dynamicId);
        dynamicMapper.delete(dynamicId);
    }

    @TransactionalForAll
    @Override
    public void publish(long dynamicId) {
        dynamicMapper.updatePublishStatus(dynamicId);
    }

    @Override
    public DynamicCreateDTO getLeastInfoByUser(long uid) {
        List<DynamicDO> dynamicList = dynamicMapper.getLeastEditByUser(uid);

        //TODO 大于1得删除之前的
        if(dynamicList.size() > 0){
            DynamicDO dynamicDO = dynamicList.get(0);
            DynamicCreateDTO response = new DynamicCreateDTO();
            List<Long> pics = dynamicInfoMapper.getByDynamicId(dynamicDO.getId());
            response.setContent(dynamicDO.getContent());
            response.setId(dynamicDO.getId());
            response.setLabels(labelService.bulkGetNameById(dynamicDO.getLabels()));
            response.setPictureIds(pics);
            response.setUid(dynamicDO.getUid());
            return response;
        }
        return null;
    }

    @TransactionalForAll
    @Override
    public void like(LikeDTO request) throws Exception{
        DynamicLikeDO byUidAndDynamicId = dynamicLikeMapper.findByUidAndDynamicId(request.getUid(), request.getDynamicId());

        if(null != byUidAndDynamicId){
            throw new BadException("已赞，不能重复点赞");
        }

        dynamicLikeMapper.like(request);

        dynamicMapper.updateLikeNum(request.getDynamicId(),1);

    }


    @TransactionalForAll
    @Override
    public void comment(CommentDTO request) {
        dynamicCommentMapper.insert(request);
        dynamicMapper.updateCommentNum(request.getDynamicId(),1);
    }

    @Override
    public DynamicInfoDTO info(long dynamicId) {
        DynamicDO dynamicDO = dynamicMapper.getById(dynamicId);
        List<Long> pics = dynamicInfoMapper.getByDynamicId(dynamicId);
        List<DynamicCommentDO> commentList = dynamicCommentMapper.getByDynamicId(dynamicId);
        Set<Long> uids = new HashSet<>();
        uids.add(dynamicDO.getUid());
        if(!commentList.isEmpty()){
            commentList.forEach(it->{
                uids.add(it.getUid());
                if(it.getReplyId() > 0){
                    uids.add(it.getReplyId());
                }
            });
        }
        Map<Long, UserDO> userMap = userMapper.listByIds(uids.stream().collect(Collectors.toList())).stream().collect(Collectors.toMap(UserDO::getId, Function.identity(), (v1, v2) -> v1));


        List<DynamicCommentDTO> commentDTOList = commentList.stream().map((it) -> {
            DynamicCommentDTO commentDTO = new DynamicCommentDTO();
            commentDTO.setCommenter(userMap.get(it.getUid()));
            if (it.getReplyId() > 0) {
                commentDTO.setReply(userMap.get(it.getReplyId()));
            }
            commentDTO.setContent(it.getContent());
            commentDTO.setId(it.getId());
            return commentDTO;
        }).collect(Collectors.toList());

        List<String> labels = labelService.bulkGetNameById(dynamicDO.getLabels());

        DynamicLikeDO byUidAndDynamicId = dynamicLikeMapper.findByUidAndDynamicId(dynamicDO.getUid(), dynamicId);

        DynamicInfoDTO response = DynamicInfoDTO.builder()
                .user(userMap.get(dynamicDO.getUid()))
                .isLike(byUidAndDynamicId == null? true:false)
                .commentList(commentDTOList)
                .labels(labels)
                .picList(pics)
                .dynamic(dynamicDO)
                .build();

        return response;

    }

    @TransactionalForAll
    @Override
    public void delComment(CommentDTO request) throws BizException {
        DynamicCommentDO dynamicCommentDO = dynamicCommentMapper.getById(request.getId());
        if(request.getUid() != dynamicCommentDO.getUid() || request.getDynamicId() != dynamicCommentDO.getDynamicId()){
            throw new BizException(401, "当前用户无权删除此评论");
        }
        dynamicCommentMapper.deleteById(request.getId());
        dynamicMapper.updateLikeNum(request.getDynamicId(), -1);
    }

    @TransactionalForAll
    @Override
    public void cancelLike(LikeDTO request) {
        DynamicLikeDO dynamicLikeDO = dynamicLikeMapper.getById(request.getId());
        if(request.getUid() != dynamicLikeDO.getUid() || request.getDynamicId() != dynamicLikeDO.getDynamicId()){
            throw new BadException("当前用户无法取消此赞");
        }
        dynamicLikeMapper.deleteById(request.getId());
        dynamicMapper.updateLikeNum(request.getDynamicId(),-1);
    }
}
