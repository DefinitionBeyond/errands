package com.campus.dev.util;

import java.util.Random;

public class GeneratorSecretUtil {

    public static String getAppSecret(long userId, String phone){
        return MD5GeneratorUtil.getMd5Utf8(phone, String.valueOf(userId));
    }


    public static String getSixValidationCode(){
        String randNum = new Random().nextInt(1000000)+"";
        System.out.println("生成"+randNum);
        if (randNum.length()!=6) {   //如果生成的不是6位数随机数则返回该方法继续生成
            return getSixValidationCode();
        }
        return randNum;
    }

}
