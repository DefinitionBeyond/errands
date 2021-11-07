package com.campus.dev.dao.mapper;

import com.campus.dev.model.ItemDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMapper {
    void insert(@Param("entity") ItemDO itemDO);

    ItemDO findById(@Param("id")long id);

    void increaseLike(@Param("id")long id);

    void decreaseLike(@Param("id")long id);


    void increaseComment(@Param("id")long id);

    void decreaseComment(@Param("id")long id);

}
