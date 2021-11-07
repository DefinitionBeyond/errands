package com.campus.dev.rest.user.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class WeChatEncryptedDataDTO {
    private String openId;
    private String nickName;
    private String gender;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String unionId;
    private Watermark watermark;
}

@Data
@NoArgsConstructor
class Watermark{
    private String appid;
    private Timestamp timestamp;
}