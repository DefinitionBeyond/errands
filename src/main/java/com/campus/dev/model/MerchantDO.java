package com.campus.dev.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MerchantDO {

    private long id;

    private String name;

    private long creator;

    private String position;

    private Date createdAt = new Date();

    private Date updatedAt;

}
