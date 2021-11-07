package com.campus.dev.dao.mapper;

import com.campus.dev.rest.dynamic.request.CommentDTO;
import com.campus.dev.model.DynamicCommentDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DynamicCommentMapper {
    void insert(@Param("entity")CommentDTO request);

    void deleteByDynamicId(@Param("dynamicId")long dynamicId);

    List<DynamicCommentDO> getByDynamicId(@Param("dynamicId") long dynamicId);

    List<DynamicCommentDO> getByDynamicIds(List<Long> dynamicIds);

    DynamicCommentDO getById(@Param("id")Long id);

    void deleteById(@Param("id")long id);
}

