package com.campus.dev.service;

import com.campus.dev.dto.ResultListDTO;
import com.campus.dev.rest.item.request.CommentRequestDTO;
import com.campus.dev.rest.item.request.DelCommentDTO;
import com.campus.dev.rest.item.request.ItemDTO;
import com.campus.dev.rest.item.request.UpdateItemDTO;
import com.campus.dev.rest.item.response.ItemDetailDTO;

import java.util.List;
import java.util.Map;

public interface ItemService {
    boolean addItem(ItemDTO itemDTO);

    boolean addComment(CommentRequestDTO requestDTO);

    boolean addLike(long itemId);

    void delComment(DelCommentDTO requestDTO);

    void cancelLike(long itemId);

    ItemDetailDTO info(Long id);

    void edit(UpdateItemDTO request);

    ResultListDTO<List<ItemDetailDTO>> list(Map<String, Object> searchMap);

}
