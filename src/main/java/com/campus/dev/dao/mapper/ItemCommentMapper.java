package com.campus.dev.dao.mapper;

import com.campus.dev.model.ItemCommentDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCommentMapper {
    void insert(@Param("entity") ItemCommentDO itemCommentDO);

    ItemCommentDO findById(@Param("id")long id);

    void deleteById(@Param("id")long id);
}
