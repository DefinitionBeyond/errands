package com.campus.dev.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {
    ACTIVE(1, "激活"),
    UNACTIVE(0, "未激活");
    private int type;
    private String desc;
}
