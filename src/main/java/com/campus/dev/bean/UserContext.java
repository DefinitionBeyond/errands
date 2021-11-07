package com.campus.dev.bean;

import com.campus.dev.model.UserDO;

import java.util.HashMap;
import java.util.Map;

public class UserContext {

    private static ThreadLocal<Map<String, Object>> threadLocal;

    /**
     * tokenKey
     */
    public static final String CONTEXT_KEY_USER_TOKEN = "token";
    /**
     * 用户idKey
     */
    public static final String CONTEXT_KEY_USER_ID = "userId";
    /**
     * 用户名
     */
    public static final String CONTEXT_KEY_USER_NAME = "userName";

    public static final String USER_INFO = "userInfo";

    static {
        threadLocal = new ThreadLocal<>();
    }

    /**
     * 设置数据
     *
     * @param key 键
     * @param value 值
     */
    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>(6);
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    /**
     * 获取数据
     *
     * @param key 键
     * @return 值
     */
    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>(6);
            threadLocal.set(map);
        }
        return map.get(key);
    }

    /**
     * 清除数据
     *
     */
    public static void remove() {
        threadLocal.remove();
    }

    public static void setUserId(String userId) {
        set(CONTEXT_KEY_USER_ID, userId);
    }

    public static Long getUserId() {
        Object value = get(CONTEXT_KEY_USER_ID);
        return Long.valueOf(String.valueOf(value));
    }

    public static void setUserName(String userName) {
        set(CONTEXT_KEY_USER_NAME, userName);
    }

    public static String getUserName() {
        Object value = get(CONTEXT_KEY_USER_NAME);
        return String.valueOf(value);
    }

    public static void setToken(String token) {
        set(CONTEXT_KEY_USER_TOKEN, token);
    }

    public static String getToken() {
        Object value = get(CONTEXT_KEY_USER_TOKEN);
        return String.valueOf(value);
    }

    public static UserDO getUserInfo() {
        Object value = get(USER_INFO);
        return (UserDO)value;
    }

    public static void setUserInfo(UserDO userDO) {
        set(USER_INFO, userDO);
    }

}