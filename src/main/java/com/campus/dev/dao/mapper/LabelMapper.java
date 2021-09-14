package com.campus.dev.dao.mapper;

import com.campus.dev.dto.request.LabelSearchDTO;
import com.campus.dev.model.LabelDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelMapper {
    void insert(@Param("entity")LabelDO label);

    void bulkInsert(@Param("list")List<LabelDO> list);

    List<LabelDO> list(@Param("search") LabelSearchDTO searchDTO);

    List<Long> listIdByName(@Param("names") List<String> names);

    List<String> bulkGetNameById(@Param("ids")List<Long> ids);

    List<LabelDO> findByTypeAndName(@Param("type")int type, @Param("name")String label);

    List<LabelDO> listIdByNames(@Param("type")int type, @Param("names")List<String> labels, @Param("uid")long uid);
}
