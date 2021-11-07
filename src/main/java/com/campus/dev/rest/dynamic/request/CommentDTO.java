package com.campus.dev.rest.dynamic.request;

import lombok.Getter;

@Getter
public class CommentDTO {
    private long id;
    private long uid;
    private long dynamicId;
    private String content;
    private long replyId;
}
