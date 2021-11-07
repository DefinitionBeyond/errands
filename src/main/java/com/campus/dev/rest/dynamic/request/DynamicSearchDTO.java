package com.campus.dev.rest.dynamic.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DynamicSearchDTO {

    private long uid;

    private long label;

    private int order = 0;

    private int status = 2;

    private int publishStatus = 1;

    private String orderBy = OrderByType.CREATED_TIME.getType();

    private String position;

}
