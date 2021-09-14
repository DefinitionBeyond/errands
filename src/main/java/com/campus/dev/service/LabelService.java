package com.campus.dev.service;

import com.campus.dev.dto.request.LabelSearchDTO;
import com.campus.dev.model.LabelDO;

import java.util.List;

public interface LabelService {

    void addLabel(int type, String label) throws Exception;

    void bulkAddLabel(int type, List<String> labels) throws Exception;

    List<LabelDO> list(LabelSearchDTO searchDTO);

    List<Long> listIdByName(List<String> names);

    List<String> bulkGetNameById(List<Long> ids);

    List<LabelDO> listByIds(List<Long> ids);

}
