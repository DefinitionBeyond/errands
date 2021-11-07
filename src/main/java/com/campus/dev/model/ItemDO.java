package com.campus.dev.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemDO {
    private long id;

    private int status;

    private String desc;

    private long showPictureUrl;

    private long price;

    private long merchantId;

    private List<Long> labels;

    private int inStock;

    private long commentNum;

    private long likeNum;

    private Date createdAt = new Date();

    private Date updatedAt;
}
