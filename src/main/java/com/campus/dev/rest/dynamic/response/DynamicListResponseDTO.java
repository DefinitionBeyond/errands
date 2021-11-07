package com.campus.dev.rest.dynamic.response;

import com.campus.dev.model.UserDO;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DynamicListResponseDTO {
    private long id;

    private UserDO user;

    private Integer status;

    private List<String> labels = new ArrayList<>();

    private List<Long> picList = new ArrayList<>();

    private boolean publishStatus;

    private boolean isLike;

    private String content;

    private long commentNum;

    private long likeNum;

    private Date createdAt;

    private Date updatedAt;

}
