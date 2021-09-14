package com.campus.dev.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DynamicLikeDO {
    private long id;
    private long uid;
    private long dynamicId;
}
