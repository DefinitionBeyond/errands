package com.campus.dev.rest.label.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabelSearchDTO {
    private int type;
    private long uid;
    private String label;
}
