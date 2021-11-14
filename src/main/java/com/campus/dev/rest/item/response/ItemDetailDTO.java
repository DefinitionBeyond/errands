package com.campus.dev.rest.item.response;

import com.campus.dev.model.LabelDO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemDetailDTO {

    private String desc;

    private String price;

    private List<LabelDO> labels;

    private int inStock;

    private List<Long> infoPictures;

    private long commentNum;

    private long likeNum;

    private boolean isLike = false;

}
