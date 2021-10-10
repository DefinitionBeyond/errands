package com.campus.dev.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SmallMealCardDO {

    private Long id;

    private String title;

    private Integer peopleNum;

    private List<Long> labels = new ArrayList<>();

    private String highLight;

    private String detailIntroduce;

    private String explain;

    private Boolean status;

    private Long creator;

    private String detailLocation;

    private Date signDeadLine;

    private Date deadline;

    private Date planStartTime;

    private Date planEndTime;

    private Date createdAt;

    private Date updatedAt;

}

