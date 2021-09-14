package com.campus.dev.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDO {

    private long id;

    private String openId;

    private String username;

    private int age;

    private int sex;

    private String phone;

    private String school;

    private long avatarUrl;

    private long idCardPosUrl;

    private long idCardNegUrl;

    private long stuCardUrl;

    private int status;

    private boolean active;

    private int level;

    private String desc;

    private Date createdAt;

    private Date updatedAt;
}


