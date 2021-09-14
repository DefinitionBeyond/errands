package com.campus.dev.dao.mapper;


import com.campus.dev.dto.request.DynamicSearchDTO;
import com.campus.dev.model.DynamicDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DynamicMapper {
    DynamicDO insert(@Param("entity") DynamicDO entity);

    void delete(@Param("dynamicId")long dynamicId);

    void updatePublishStatus(@Param("dynamicId")long dynamicId);

    List<DynamicDO> getLeastEditByUser(@Param("uid")long uid);

    void updateLikeNum(@Param("dynamicId")long dynamicId, @Param("op")int op);

    void updateCommentNum(@Param("dynamicId")long dynamicId, @Param("op")int op);

    DynamicDO getById(@Param("dynamicId")long dynamicId);

    List<DynamicDO> list(DynamicSearchDTO searchDTO);
}
