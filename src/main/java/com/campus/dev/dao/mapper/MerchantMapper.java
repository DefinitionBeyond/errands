package com.campus.dev.dao.mapper;

import com.campus.dev.model.MerchantDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantMapper {
    MerchantDO findByCreator(@Param("creator") Long creator);
}
