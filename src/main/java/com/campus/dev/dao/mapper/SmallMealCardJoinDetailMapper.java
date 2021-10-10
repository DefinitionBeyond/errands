package com.campus.dev.dao.mapper;

import com.campus.dev.model.SmallMealCardJoinDetailDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SmallMealCardJoinDetailMapper {
    void insert(@Param("smallMealCardId") long smallMealCardId, @Param("participant") long participant);

    SmallMealCardJoinDetailDO findBySmallMealCardIdAndParticipant(@Param("smallMealCardId") long smallMealCardId, @Param("participant") long participant);

    void deleteBySmallMealCardId(@Param("smallMealCardId")long smallMealCardId);
}
