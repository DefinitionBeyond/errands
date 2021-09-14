package com.campus.dev.util;

import java.util.regex.Pattern;

public class StringRuleUtil {

    private static final String specialRegex = "^[\\w\\-\\u4e00-\\u9fa5（）—，。《》【】「」 ；‘’“”、/#^+~%℃()-,.<>\\[\\]\\{\\};'\"]{1,50}$";

    private static final String base = "^[\\u4e00-\\u9fa5\\da-zA-z]{1,50}$";

    private static Pattern pattern = Pattern.compile(specialRegex);

    public static boolean checkSpecialChar(String str){
        return pattern.matches(base, str);
    }
}
