package com.campus.dev.bean;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HttpRequestOptions {

    @Autowired
    private HttpServletRequest httpServletRequest;

    public static final String USER_ID_HEADER_NAME = "X-User-Id";

    public Long getUserId() throws BadException {
        String userId = httpServletRequest.getHeader(USER_ID_HEADER_NAME);
        return Long.valueOf(userId);
    }

}
