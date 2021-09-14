package com.campus.dev.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DynamicCommentDO {

    private long id;

    private long uid;

    private long dynamicId;

    private String content;

    private long replyId;
}
