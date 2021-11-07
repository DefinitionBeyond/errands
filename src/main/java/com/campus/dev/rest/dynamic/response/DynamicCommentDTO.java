package com.campus.dev.rest.dynamic.response;

import com.campus.dev.model.UserDO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DynamicCommentDTO {
    private UserDO commenter;
    private UserDO reply;
    private String content;
    private long id;
}
