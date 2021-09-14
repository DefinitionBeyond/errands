package com.campus.dev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Code2SessionDTO {

    //用户唯一标识
    private String openid;
    // 会话密钥
    private String session_key;
    // 用户在开放平台的唯一标识符
    private String unionid;
    // 错误码
    private Integer errcode;
    // 错误信息
    private String errmsg;

}