package com.campus.dev.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

//@Log4j2
@Component
public class UserIdentifyCodeManage {
    //监听器，监听被淘汰的数据
    private RemovalListener<String, String> listener = removalNotification -> {};
//    log.debug(removalNotification.getKey() + " is removed from local cache");

    @Value("${tecent.send-message.local.cache.max.size}")
    private Long maxSize;

    @Value("${tecent.send-message.local.cache.expire.minutes}")
    private Long expire;

    private Cache<String, String> apps_info;

    @PostConstruct
    public void init() {
        apps_info = CacheBuilder.newBuilder()
                .maximumSize(maxSize)//超过size的元素按照lru进行淘汰
                .expireAfterWrite(expire, TimeUnit.MINUTES) //该appid在写入cache20分钟后就过期
                .removalListener(listener)//添加移除appId的监听器
//            .maximumWeight(100)//设置内存上限
                .build();
    }

    public String getUserIdentifyCode(String phone) {
        return apps_info.getIfPresent(phone);
    }

    public String updateUserIdentifyCode(String phone, String code) {
        if(!StringUtils.hasText(apps_info.getIfPresent(phone))){
            apps_info.put(phone, code);
            return code;
        }
        return this.getUserIdentifyCode(phone);
    }
}
