package com.campus.dev.model;


import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemCommentDO {

    private long id;

    private long uid;

    private long itemId;

    private String content;

    private long replyId;

    private Date createdAt = new Date();

    private Date updatedAt;
}
