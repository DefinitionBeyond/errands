package com.campus.dev.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SMsProperties {
    // 短信应用SDK AppID
    // 1400开头
    public int appId;

    // 短信应用SDK AppKey
    public String appKey;

    // 需要发送短信的手机号码
    // String[] phoneNumbers = {"15212111830"};

    // 短信模板ID，需要在短信应用中申请
    //NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
    public int templateId;

    // 签名
    // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
    public String smsSign;

    @Value("${tecent.send-message.appId")
    public void setAppId(int appId) {
        this.appId = appId;
    }

    @Value("${tecent.send-message.appKey")
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Value("${tecent.send-message.templateId")
    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    @Value("${tecent.send-message.smsSign")
    public void setSmsSign(String smsSign) {
        this.smsSign = smsSign;
    }
}
