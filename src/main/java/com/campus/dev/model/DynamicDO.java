package com.campus.dev.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DynamicDO {
    private long id;

    private long uid;

    private Integer status;

    private List<Long> labels = new ArrayList<>();

    private boolean publishStatus;

    private String content;

    private long commentNum;

    private long likeNum;

    private Date createdAt = new Date();

    private Date updatedAt;

}
