package com.campus.dev.service;

import com.campus.dev.rest.item.request.CommentRequestDTO;
import com.campus.dev.rest.item.request.DelCommentDTO;
import com.campus.dev.rest.item.request.ItemDTO;
import com.campus.dev.rest.item.response.ItemDetailDTO;

public interface ItemService {
    boolean addItem(ItemDTO itemDTO);

    boolean addComment(CommentRequestDTO requestDTO);

    boolean addLike(long itemId);

    void delComment(DelCommentDTO requestDTO);

    void cancelLike(long itemId);

    ItemDetailDTO info(Long id);
}
