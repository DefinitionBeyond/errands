package com.campus.dev.rest.item.request;

import lombok.Getter;

@Getter
public class UpdateItemDTO {

    private long itemId;

    private String desc;

    private long showPictureUrl;

    private String price;

    private int inStock;
}
