package com.campus.dev.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeiXinPostParamConstant {

    @Value("${base.weixin.appid}")
    public String APP_ID;

    @Value("${base.weixin.appSecret}")
    public String SECRET;

    @Value("${base.weixin.session-url}")
    public String SESSION_URL;
}
