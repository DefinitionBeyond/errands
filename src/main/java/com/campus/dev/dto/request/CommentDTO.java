package com.campus.dev.dto.request;

import lombok.Getter;

@Getter
public class CommentDTO {
    private long id;
    private long uid;
    private long dynamicId;
    private String content;
    private long replyId;
}
