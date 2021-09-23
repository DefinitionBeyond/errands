package com.campus.dev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditDTO{

    private long id;

    private String username;

    private int age;

    private int sex;

    private String phone;

    private String school;

    private String avatarUrl;

    private String desc;
}