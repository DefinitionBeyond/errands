package com.campus.dev.service;

import com.campus.dev.bean.BizException;
import com.campus.dev.dto.request.CommentDTO;
import com.campus.dev.dto.request.DynamicCreateDTO;
import com.campus.dev.dto.request.DynamicListDTO;
import com.campus.dev.dto.request.LikeDTO;
import com.campus.dev.dto.response.DynamicInfoDTO;
import com.campus.dev.dto.response.DynamicListResponseDTO;

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
