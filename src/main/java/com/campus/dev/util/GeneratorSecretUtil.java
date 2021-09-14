package com.campus.dev.util;

public class GeneratorSecretUtil {

    public static String getAppSecret(long userId, String phone){
        return MD5GeneratorUtil.getMd5Utf8(phone, String.valueOf(userId));
    }


}
