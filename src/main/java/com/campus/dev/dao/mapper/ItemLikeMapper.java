package com.campus.dev.dao.mapper;

import com.campus.dev.model.ItemLikeDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemLikeMapper {
    void insert(ItemLikeDO itemLikeDO);

    ItemLikeDO findByUidAndItemid(@Param("uid")Long uid, @Param("itemId") long itemId);

    void deleteById(@Param("id")long id);

    List<ItemLikeDO> findByUidAndItemids(@Param("uid")Long uid, @Param("itemIds")List<Long> itemIds);
}
