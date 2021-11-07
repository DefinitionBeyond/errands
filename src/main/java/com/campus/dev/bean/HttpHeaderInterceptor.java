package com.campus.dev.bean;

import com.campus.dev.dao.mapper.UserMapper;
import com.campus.dev.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static com.campus.dev.bean.HttpRequestOptions.USER_ID_HEADER_NAME;

@Component
public class HttpHeaderInterceptor implements Filter {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();

        if(uri.startsWith("/v1/user"))return;

        String userIdHeader =  request.getHeader(USER_ID_HEADER_NAME);

        if(userIdHeader.isEmpty()) {
            throw new BadException("X-User-Id 为空");
        }

        Long userId = Long.valueOf(userIdHeader);

        if(userId <= 0 ) {
            throw new BadException("X-User-Id 不合法");
        }

        UserContext.set(UserContext.CONTEXT_KEY_USER_ID, userId);

        UserDO userDO = userMapper.getById(userId);

        if(userDO == null)throw new BadException("该用户不存在系统");

        UserContext.set(UserContext.USER_INFO, userDO);
    }
}
