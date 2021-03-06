package com.campus.dev.rest.user.request;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ActiveUserDTO {
    private long operator;

    private List<Long> uids = new ArrayList<>();
}
