package com.campus.dev.rest.card.response;

import com.campus.dev.model.UserDO;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SmallMealCardDetailDTO {

    private Long id;

    private boolean joinStatus;

    private String title;

    private Integer peopleNum;

    private List<String> labels = new ArrayList<>();

    private String highLight;

    private String detailIntroduce;

    private String explain;

    private Boolean status;

    private UserDO creator;

    private String detailLocation;

    private Date signDeadLine;

    private Date deadline;

    private Date planStartTime;

    private Date planEndTime;

    private Date createdAt;

    private Date updatedAt;

}
