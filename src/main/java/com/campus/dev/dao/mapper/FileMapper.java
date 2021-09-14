package com.campus.dev.dao.mapper;

import com.campus.dev.model.FileDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FileMapper {

    void insert(@Param("entity") FileDO fileDO);

    void bulkInsert(List<FileDO> list);

    List<FileDO> list(@Param("type") int type);

    FileDO getById(@Param("id") long id);
}
