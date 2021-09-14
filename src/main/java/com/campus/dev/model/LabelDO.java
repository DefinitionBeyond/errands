package com.campus.dev.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LabelDO {

    private long id;

    private String labelName;

    private int type;

    private long uid;

    private Date createdAt = new Date();

    private Date updatedAt;
}
