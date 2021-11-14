package com.campus.dev.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemInfoDO {

    public ItemInfoDO(long itemId, long infoPicture) {
        this.itemId = itemId;
        this.infoPicture = infoPicture;
    }

    private long id;

    private long itemId;

    private long infoPicture;
}
