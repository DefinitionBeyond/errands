package com.campus.dev.rest.dynamic.response;

import com.campus.dev.model.DynamicDO;
import com.campus.dev.model.UserDO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DynamicInfoDTO {
    private UserDO user;

    private DynamicDO dynamic;

    private List<Long> picList;

    private List<String> labels;

    private boolean isLike;

    private List<DynamicCommentDTO> commentList;
}
