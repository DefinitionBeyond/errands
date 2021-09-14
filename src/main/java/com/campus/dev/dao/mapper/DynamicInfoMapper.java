package com.campus.dev.dao.mapper;

import com.campus.dev.model.DynamicInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.method.HandlerMethod;

import java.util.List;

@Repository
public interface DynamicInfoMapper {

    void bulkInsert(@Param("list")List<DynamicInfoDO> entities);

    List<Long> getByDynamicId(@Param("dynamicId")long dynamicId);

    void deleteByDynamicId(@Param("dynamicId") long dynamicId);

    List<DynamicInfoDO> getByDynamicIds(@Param("dynamicIds")List<Long> dynamicIds);
}
