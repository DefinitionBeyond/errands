package com.campus.dev.service;

import com.campus.dev.bean.BizException;
import com.campus.dev.dto.ResultListDTO;
import com.campus.dev.rest.card.request.PublishSmallMealCardDTO;
import com.campus.dev.rest.card.response.SmallMealCardDetailDTO;
import com.campus.dev.model.SmallMealCardDO;

import java.util.List;
import java.util.Map;

public interface SmallMealCardService {
    void publish(PublishSmallMealCardDTO request);

    ResultListDTO<List<SmallMealCardDO>> list(Map<String, Object> map);

    boolean joinById(long smallMealCardId, long participant) throws BizException;

    SmallMealCardDetailDTO info(long smallMealCardId, long userId) throws BizException;

    Object delete(long smallMealCardId, long userId)throws BizException;

}
