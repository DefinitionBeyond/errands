package com.campus.dev.dao.mapper;

import com.campus.dev.model.MerchantStaffDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantStaffMapper {
    List<MerchantStaffDO> list(@Param("entity") MerchantStaffDO entity);

}
