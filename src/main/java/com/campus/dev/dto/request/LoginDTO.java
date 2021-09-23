package com.campus.dev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String username;
    private String phone;
    private String openId;
    private String code;
    private String identifyCode;
    private String encryptedData;
    private String sessionKey;
    private String iv;
}
