package com.campus.dev.rest.item.request;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemDTO {

    private String desc;

    private long showPictureUrl;

    private String price;

    private List<Long> labels;

    private int inStock;

    private List<Long> infoPictures;

}
