package com.campus.dev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {
    private long id;
    private long uid;
    private long dynamicId;
}
