package com.campus.dev.dto.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublishSmallMealCardDTO {

    private Long creator;

    private String title;

    private List<Long> labels = new ArrayList<>();

    private String highLight;

    private String detailIntroduce;

    private String explain;

    private String detailLocation;

    private String deadline;

    private String planStartTime;

    private String planEndTime;

    private String signDeadLine;
}
