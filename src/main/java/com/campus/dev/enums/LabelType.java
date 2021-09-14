package com.campus.dev.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum LabelType {
    USER_CUSTOM(1, "用户标签"),
    SYSTEM(0,"系统标签"),
    DYNAMIC(2, "动态标签");

    Integer type;
    String desc;
}
