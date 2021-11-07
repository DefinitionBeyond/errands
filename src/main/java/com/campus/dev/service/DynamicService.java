package com.campus.dev.service;

import com.campus.dev.bean.BizException;
import com.campus.dev.rest.dynamic.request.CommentDTO;
import com.campus.dev.rest.dynamic.request.DynamicCreateDTO;
import com.campus.dev.rest.dynamic.request.DynamicListDTO;
import com.campus.dev.rest.dynamic.request.LikeDTO;
import com.campus.dev.rest.dynamic.response.DynamicInfoDTO;

import java.util.List;

public interface DynamicService {

    void create(DynamicCreateDTO dynamicDO, boolean isPublish);

    List<DynamicInfoDTO> list(DynamicListDTO request);

    void delete(long dynamicId);

    void publish(long dynamicId);

    DynamicCreateDTO getLeastInfoByUser(long uid);

    void like(LikeDTO request) throws Exception;

    void comment(CommentDTO request);

    DynamicInfoDTO info(long dynamicId);

    void delComment(CommentDTO request) throws BizException;

    void cancelLike(LikeDTO request);
}
