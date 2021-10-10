package com.campus.dev.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SmallMealCardJoinDetailDO {

    private Long id;

    private Long smallMealCardId;

    private Long participant;

    private Date createdAt;

    private Date updatedAt;

}