package com.campus.dev.service.impl;

import com.campus.dev.bean.BadException;
import com.campus.dev.bean.BizException;
import com.campus.dev.bean.HttpRequestOptions;
import com.campus.dev.bean.TransactionalForAll;
import com.campus.dev.dao.mapper.SmallMealCardJoinDetailMapper;
import com.campus.dev.dao.mapper.SmallMealCardMapper;
import com.campus.dev.dao.mapper.UserMapper;
import com.campus.dev.dto.ResultListDTO;
import com.campus.dev.rest.card.request.PublishSmallMealCardDTO;
import com.campus.dev.rest.card.response.SmallMealCardDetailDTO;
import com.campus.dev.model.SmallMealCardDO;
import com.campus.dev.model.SmallMealCardJoinDetailDO;
import com.campus.dev.model.UserDO;
import com.campus.dev.service.SmallMealCardService;
import com.campus.dev.util.TimeUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class SmallMealCardServiceImpl implements SmallMealCardService {

    @Autowired
    private SmallMealCardMapper smallMealCardMapper;

    @Autowired
    private SmallMealCardJoinDetailMapper smallMealCardJoinDetailMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpRequestOptions httpRequestOptions;

    @Override
    public void publish(PublishSmallMealCardDTO request) {
        checkParam(request);
        long userId = httpRequestOptions.getUserId();
        if(userId == 0 )throw new BadException("Plz 提供合法的user id");

        SmallMealCardDO smallMealCardDO = new SmallMealCardDO();
        smallMealCardDO.setCreator(userId);

        //TODO param transfer
        smallMealCardMapper.add(smallMealCardDO);

    }

    @Override
    public ResultListDTO<List<SmallMealCardDO>> list(Map<String, Object> map) {
        Page<SmallMealCardDO> pageHelper = PageHelper.startPage((int) map.get("page"), (int) map.get("size"));
        List<SmallMealCardDO> result = smallMealCardMapper.list(map);
        return new ResultListDTO<List<SmallMealCardDO>>(result, pageHelper.getTotal(),pageHelper.getPageNum());
    }

    @TransactionalForAll
    @Override
    public boolean joinById(long smallMealCardId, long participant) throws BizException {
        String currentTime = TimeUtils.formatStampYYMMDDHHMMSS(System.currentTimeMillis());

        SmallMealCardDO byId = smallMealCardMapper.findByIdAndTime(smallMealCardId, currentTime);
        if(byId == null)throw new BizException(400, "活动已经过期");
        SmallMealCardJoinDetailDO bySmallMealCardIdAndParticipant = smallMealCardJoinDetailMapper.findBySmallMealCardIdAndParticipant(smallMealCardId, participant);
        if(bySmallMealCardIdAndParticipant != null)throw new BizException(400, "已参与，不需重复参与");
        smallMealCardJoinDetailMapper.insert(smallMealCardId, participant);
        smallMealCardMapper.addParticipantNum(smallMealCardId);
        return true;
    }

    @Override
    public SmallMealCardDetailDTO info(long smallMealCardId, long participant) throws BizException {
        UserDO userDO = userMapper.findById(participant);

        SmallMealCardDO byId = smallMealCardMapper.findById(smallMealCardId);
        if(byId == null) throw new BizException(400, "不存在的活动");
        SmallMealCardJoinDetailDO bySmallMealCardIdAndParticipant = smallMealCardJoinDetailMapper.findBySmallMealCardIdAndParticipant(smallMealCardId, participant);
        SmallMealCardDetailDTO smallMealCardDetailDTO = this.convertDetail(byId);

        smallMealCardDetailDTO.setCreator(userDO);
        smallMealCardDetailDTO.setJoinStatus(bySmallMealCardIdAndParticipant == null?false:true);
        return smallMealCardDetailDTO;

    }

    @TransactionalForAll
    @Override
    public Object delete(long smallMealCardId, long userId) throws BizException {
        SmallMealCardDO byId = smallMealCardMapper.findById(smallMealCardId);
        if(byId == null) throw new BizException(400, "不存在的活动");
        if(byId.getCreator() != userId) throw new BizException(401, "无权限删除");
        smallMealCardJoinDetailMapper.deleteBySmallMealCardId(smallMealCardId);
        smallMealCardMapper.deleteById(smallMealCardId);
        return null;
    }

    private SmallMealCardDetailDTO convertDetail(SmallMealCardDO smallMealCardDO){
        return SmallMealCardDetailDTO.builder()
                .id(smallMealCardDO.getId())
                .deadline(smallMealCardDO.getDeadline())
                .explain(smallMealCardDO.getExplain())
                .createdAt(smallMealCardDO.getCreatedAt())
                .updatedAt(smallMealCardDO.getUpdatedAt())
                .detailIntroduce(smallMealCardDO.getDetailIntroduce())
                .detailLocation(smallMealCardDO.getDetailLocation())
                .signDeadLine(smallMealCardDO.getSignDeadLine())
                .status(smallMealCardDO.getStatus())
                .planStartTime(smallMealCardDO.getPlanStartTime())
                .peopleNum(smallMealCardDO.getPeopleNum())
                .planEndTime(smallMealCardDO.getPlanEndTime())
                .highLight(smallMealCardDO.getHighLight())
                .build();
    }


    private void checkParam(PublishSmallMealCardDTO request){
        if(!StringUtils.hasText(request.getTitle()))throw new BadException("Title not null");
        if(!StringUtils.hasText(request.getDeadline()))throw new BadException("Deadline not null");
        if(!StringUtils.hasText(request.getPlanEndTime())) throw new BadException("Plan end time not null");
        if(!StringUtils.hasText(request.getPlanStartTime())) throw new BadException("Plan start time not null");
    }
}
