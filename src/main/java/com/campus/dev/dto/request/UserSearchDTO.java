package com.campus.dev.dto.request;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSearchDTO {
    private long id;

    private String username;

    private String phone;

    private String school;

    private int status;

    private boolean active;

    private String creatTimeStart;

    private String creatTimeEnd;
}
