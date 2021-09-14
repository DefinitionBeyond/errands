package com.campus.dev.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class DynamicCreateDTO {

    private long id;

    private long uid;

    private Integer status;

    private List<String> labels = new ArrayList<>();

    private String content;

    private List<Long> pictureIds;

}
