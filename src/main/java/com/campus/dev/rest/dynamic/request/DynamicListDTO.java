package com.campus.dev.rest.dynamic.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class DynamicListDTO {

    private long id;

    private long uid;

    private String label;

    private int order = 0;

    private int status = 2;

    private String orderBy = OrderByType.CREATED_TIME.getType();

    private String position;

}

@Getter
@AllArgsConstructor
enum OrderByType{
    CREATED_TIME("created_at"),
    COMMENT_NUM("comment"),
    LIKE_NUM("like");


    private String type;
}
