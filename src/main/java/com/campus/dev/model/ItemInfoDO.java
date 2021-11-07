package com.campus.dev.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemInfoDO {

    private long id;

    private long itemId;

    private long infoPicture;
}
