package com.campus.dev.rest.item.request;

import lombok.Getter;

@Getter
public class CommentRequestDTO {
    private long itemId;

    private String content;

    private long replyId;

}
