package com.campus.dev.service.impl;

import com.campus.dev.bean.BadException;
import com.campus.dev.bean.BizException;
import com.campus.dev.bean.HttpRequestOptions;
import com.campus.dev.bean.TransactionalForAll;
import com.campus.dev.dao.mapper.LabelMapper;
import com.campus.dev.rest.label.request.LabelSearchDTO;
import com.campus.dev.enums.LabelType;
import com.campus.dev.model.LabelDO;
import com.campus.dev.service.LabelService;
import com.campus.dev.util.StringRuleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LabelServiceImpl implements LabelService {
    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private HttpRequestOptions httpRequestOptions;

    @TransactionalForAll
    @Override
    public void addLabel(int type, String label) throws BadException,BizException {
        if(!StringRuleUtil.checkSpecialChar(label))throw new BadException("不合法的标签");
        if(type == 0) throw new BizException(401, "您无权限增加系统标签");
        List<LabelDO> labelList = labelMapper.findByTypeAndName(type, label);
        if(!CollectionUtils.isEmpty(labelList))throw new BadException("已存在的标签");

        LabelDO labelDO = LabelDO.builder()
                .labelName(label)
                .type(type)
                .uid(type == LabelType.USER_CUSTOM.getType()? httpRequestOptions.getUserId():0)
                .build();
        labelMapper.insert(labelDO);
    }

    @TransactionalForAll
    @Override
    public void bulkAddLabel(int type, List<String> labels) throws BadException,BizException  {
        if(CollectionUtils.isEmpty(labels))return;
        labels.forEach(label->{if(!StringRuleUtil.checkSpecialChar(label))throw new BadException("不合法的标签");});
        if(type == 0) throw new BizException(401, "您无权限增加系统标签");
        long uid = type == LabelType.USER_CUSTOM.getType()? httpRequestOptions.getUserId():0;
        List<LabelDO> labelDOS = labelMapper.listIdByNames(type, labels, uid);

        List<LabelDO> collect = labelDOS.stream().map(LabelDO::getLabelName).filter(it -> !labelDOS.contains(it))
                .map(it-> LabelDO.builder().labelName(it).type(type).uid(uid).createdAt(new Date()).build()
        ).collect(Collectors.toList());
        labelMapper.bulkInsert(collect);
    }

    @Override
    public List<LabelDO> list(LabelSearchDTO searchDTO) {

        return labelMapper.list(searchDTO);
    }

    @Override
    public List<Long> listIdByName(List<String> names) {
        if(CollectionUtils.isEmpty(names)) return new ArrayList<>();
        return labelMapper.listIdByName(names);
    }

    @Override
    public List<String> bulkGetNameById(List<Long> ids) {
        if(CollectionUtils.isEmpty(ids)) return new ArrayList<>();
        return labelMapper.bulkGetNameById(ids);
    }

    @Override
    public List<LabelDO> listByIds(List<Long> ids) {
        return null;
    }


}
