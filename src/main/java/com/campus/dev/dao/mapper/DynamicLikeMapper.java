package com.campus.dev.dao.mapper;

import com.campus.dev.rest.dynamic.request.LikeDTO;
import com.campus.dev.model.DynamicLikeDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DynamicLikeMapper {
    void like(@Param("entity") LikeDTO entity);

    DynamicLikeDO findByUidAndDynamicId(@Param("uid") long uid, @Param("dynamicId")long dynamicId);

    void deleteByDynamicId(@Param("dynamicId")long dynamicId);

    List<DynamicLikeDO> getByDynamicIds(@Param("dynamicIds")List<Long> dynamicIds);

    DynamicLikeDO getById(@Param("id")long id);

    void deleteById(@Param("id")long id);
}

