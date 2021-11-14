package com.campus.dev.dao.mapper;

import com.campus.dev.model.ItemInfoDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemInfoMapper {

    List<ItemInfoDO> findByItemId(@Param("itemId") Long itemId);

    void bulkInsert(List<ItemInfoDO> itemInfoDOS);
}
