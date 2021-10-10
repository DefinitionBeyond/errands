package com.campus.dev.dao.mapper;

import com.campus.dev.model.SmallMealCardDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SmallMealCardMapper {
    void add(SmallMealCardDO smallMealCardDO);


    List<SmallMealCardDO> list(Map<String, Object> map);

    void addParticipantNum(@Param("id") long id);

    SmallMealCardDO findByIdAndTime(@Param("id")long id,@Param("currentTime") String currentTime);

    SmallMealCardDO findById(@Param("id")long id);

    void deleteById(@Param("id")long id);
}
