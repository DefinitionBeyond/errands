package com.campus.dev.service.impl;

import com.campus.dev.bean.*;
import com.campus.dev.dao.mapper.*;
import com.campus.dev.dto.ResultListDTO;
import com.campus.dev.model.*;
import com.campus.dev.rest.item.request.CommentRequestDTO;
import com.campus.dev.rest.item.request.DelCommentDTO;
import com.campus.dev.rest.item.request.ItemDTO;
import com.campus.dev.rest.item.request.UpdateItemDTO;
import com.campus.dev.rest.item.response.ItemDetailDTO;
import com.campus.dev.service.ItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemCommentMapper itemCommentMapper;

    @Autowired
    private ItemLikeMapper itemLikeMapper;

    @Autowired
    private MerchantStaffMapper merchantStaffMapper;

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private ItemInfoMapper itemInfoMapper;

    @TransactionalForAll
    @Override
    public boolean addItem(ItemDTO itemDTO) {
        checkMerchant();
        long price = 0;
        try {
            price = changePrice(itemDTO.getPrice());
        }catch (NumberFormatException e){
            throw new BadException("Price format error");
        }catch (BizException e){
            throw e;
        }

        MerchantDO merchantDO = merchantMapper.findByCreator(UserContext.getUserId());

        if(null == merchantDO){
            log.warn("不是商户，发出发布商品请求，userId{}", UserContext.getUserId());
            throw new BadException("商户不存在");
        }

        ItemDO itemDO = ItemDO.builder()
                .labels(itemDTO.getLabels())
                .inStock(itemDTO.getInStock())
                .price(price)
                .merchantId(merchantDO.getId())
                .showPictureUrl(itemDTO.getShowPictureUrl())
                .desc(itemDTO.getDesc())
                .build();

        itemMapper.insert(itemDO);



        if(!CollectionUtils.isEmpty(itemDTO.getInfoPictures())) {
            long itemId = itemDO.getId();
            List<ItemInfoDO> itemInfoDOS = itemDTO.getInfoPictures().stream().map(it -> new ItemInfoDO(itemId, it)).collect(Collectors.toList());


            itemInfoMapper.bulkInsert(itemInfoDOS);
        }

        return true;
    }

    @TransactionalForAll
    @Override
    public boolean addComment(CommentRequestDTO requestDTO) {
        checkItemExist(requestDTO.getItemId());
        ItemCommentDO itemCommentDO = ItemCommentDO.builder()
                .itemId(requestDTO.getItemId())
                .replyId(requestDTO.getReplyId())
                .content(requestDTO.getContent())
                .uid(UserContext.getUserId())
                .build();

        itemCommentMapper.insert(itemCommentDO);

        itemMapper.increaseComment(requestDTO.getItemId());
        return true;
    }

    @TransactionalForAll
    @Override
    public boolean addLike(long itemId) {
        checkItemExist(itemId);
        ItemLikeDO itemLikeDO = ItemLikeDO.builder()
                .itemId(itemId)
                .uid(UserContext.getUserId())
                .build();

        itemLikeMapper.insert(itemLikeDO);

        itemMapper.increaseLike(itemId);
        return true;
    }

    @TransactionalForAll
    @Override
    public void delComment(DelCommentDTO requestDTO) {
        ItemDO itemDO = checkItemExist(requestDTO.getItemId());
        ItemCommentDO itemCommentDO = itemCommentMapper.findById(requestDTO.getCommentId());
        List<MerchantStaffDO> list = merchantStaffMapper.list(MerchantStaffDO.builder()
                .merchantId(itemDO.getMerchantId())
                .staffId(UserContext.getUserId())
                .status(1)
                .build());
        if(itemCommentDO.getUid() != UserContext.getUserId() && CollectionUtils.isEmpty(list)){
            throw new BizException(401,"无权限删除此评论");
        }

        itemMapper.decreaseComment(itemDO.getId());
        itemCommentMapper.deleteById(requestDTO.getCommentId());
    }

    @TransactionalForAll
    @Override
    public void cancelLike(long itemId) {
        checkItemExist(itemId);
        ItemLikeDO itemLikeDO = itemLikeMapper.findByUidAndItemid(UserContext.getUserId(), itemId);

        if(null == itemLikeDO){
            throw new BizException(400,"无效操作");
        }

        itemLikeMapper.deleteById(itemLikeDO.getId());
        itemMapper.decreaseLike(itemId);
    }

    @Override
    public ItemDetailDTO info(Long id) {
        ItemDO itemDO = checkItemExist(id);

        ItemDetailDTO itemDetailDTO = ItemDetailDTO.builder()
                .desc(itemDO.getDesc())
                .commentNum(itemDO.getCommentNum())
                .likeNum(itemDO.getLikeNum())
                .inStock(itemDO.getInStock())
                .price(changeShowPrice(itemDO.getPrice()))
                .build();
        if(!CollectionUtils.isEmpty(itemDO.getLabels())) {
            List<LabelDO> labels = labelMapper.listByIds(itemDO.getLabels());
            itemDetailDTO.setLabels(labels);
        }

        List<ItemInfoDO> itemInfoDOS = itemInfoMapper.findByItemId(id);

        if(!CollectionUtils.isEmpty(itemInfoDOS)) {
            itemDetailDTO.setInfoPictures(itemInfoDOS.stream().map(ItemInfoDO::getInfoPicture).collect(Collectors.toList()));
        }

        ItemLikeDO byUidAndItemid = itemLikeMapper.findByUidAndItemid(UserContext.getUserId(), itemDO.getId());

        if(null != byUidAndItemid){
            itemDetailDTO.setLike(true);
        }

        return itemDetailDTO;
    }

    @Override
    public void edit(UpdateItemDTO request) {
        checkMerchant();

        MerchantDO merchantDO = merchantMapper.findByCreator(UserContext.getUserId());

        if(null == merchantDO){
            log.warn("不是商户，发出发布商品请求，userId{}", UserContext.getUserId());
            throw new BadException("商户不存在");
        }

        ItemDO itemDO = checkItemExist(request.getItemId());

        if(StringUtils.hasText(request.getDesc())){
            itemDO.setDesc(request.getDesc());
        }

        if(StringUtils.hasText(request.getPrice())){
            itemDO.setPrice(changePrice(request.getPrice()));
        }

        if(request.getInStock()>0){
            itemDO.setInStock(request.getInStock());
        }

        if(request.getShowPictureUrl()>0 && request.getShowPictureUrl()!= itemDO.getShowPictureUrl()){
            itemDO.setShowPictureUrl(request.getShowPictureUrl());
        }

        itemMapper.update(itemDO);

    }

    @Override
    public ResultListDTO<List<ItemDetailDTO>> list(Map<String, Object> searchMap) {
        if(CollectionUtils.isEmpty(searchMap))return null;

        Page<ItemDO> startPage = PageHelper.startPage((Integer) searchMap.getOrDefault("page", 1), (Integer) searchMap.getOrDefault("size", 20));
        List<ItemDO> result = itemMapper.list(searchMap);

        List<Long> itemIds = result.stream().map(ItemDO::getId).collect(Collectors.toList());

        List<ItemLikeDO> byUidAndItemids = itemLikeMapper.findByUidAndItemids(UserContext.getUserId(), itemIds);
        List<Long> curUserLikeItem = new ArrayList<>();
        if(!CollectionUtils.isEmpty(byUidAndItemids)){
            curUserLikeItem = byUidAndItemids.stream().map(ItemLikeDO::getId).collect(Collectors.toList());
        }

        Set<Long> labels = new HashSet<>();
        result.forEach(it->labels.addAll(it.getLabels()));

        Map<Long, LabelDO> labelMap = labelMapper.listByIds(labels.stream().collect(Collectors.toList()))
                .stream()
                .collect(Collectors.toMap(LabelDO::getId, Function.identity(),(v1, v2) -> v1));

        return new ResultListDTO(result.stream().map(it->{
            List<LabelDO> tempLabel = new ArrayList<>();
            if(!CollectionUtils.isEmpty(it.getLabels())){
                it.getLabels().forEach(l->{
                    tempLabel.add(labelMap.get(l));
                });
            }
            return ItemDetailDTO.builder()
                    .desc(it.getDesc())
                    .price(changeShowPrice(it.getPrice()))
                    .inStock(it.getInStock())
                    .likeNum(it.getLikeNum())
                    .commentNum(it.getCommentNum())
                    .labels(tempLabel)
                    .build();
        }).collect(Collectors.toList()),startPage.getTotal(),startPage.getPageNum());
    }

    private void checkMerchant() {
        if(UserContext.getUserInfo().getIsMerchant() == 0)
            throw new BizException(401,"您无权限操作商品");

    }

    private ItemDO checkItemExist(long itemId){
        ItemDO itemDO = itemMapper.findById(itemId);
        if(null == itemDO) throw new  BadException("商品不存在");

        return itemDO;
    }

    private String changeShowPrice(long price){
        return String.valueOf(price/100);
    }

    private long changePrice(String price){
        if(!price.contains(".")){
            return Long.parseLong(price)*100;
        }
        String[] split = price.split(".");
        if(split.length == 1){
            return Long.parseLong(split[0])*100;
        }
        else if(split.length == 2){
            return Long.parseLong(split[0])*100+Long.parseLong(split[1]);
        }else {
            throw new BadException("Price format error");
        }

    }
}
