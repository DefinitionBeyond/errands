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


@Log4j2
@Component
public class UserSessionKeyCache {
    //监听器，监听被淘汰的数据
    private RemovalListener<String, String> listener = removalNotification -> log.debug(removalNotification.getKey() + " is removed from local cache");

    @Value("${tecent.send-message.local.cache.max.size}")
    private Long maxSize;

    @Value("${tecent.send-message.local.cache.expire.minutes}")
    private Long expire;

    private Cache<String, String> sessionInfo;

    @PostConstruct
    public void init() {
        sessionInfo = CacheBuilder.newBuilder()
                .maximumSize(maxSize)//超过size的元素按照lru进行淘汰
                .expireAfterWrite(expire, TimeUnit.MINUTES) //该appid在写入cache20分钟后就过期
                .removalListener(listener)//添加移除appId的监听器
//            .maximumWeight(100)//设置内存上限
                .build();
    }

    public String getSessionKey(String openId) {
        return sessionInfo.getIfPresent(openId);
    }

    public String updateSessionKey(String openId, String sessionKey) {
        if(!StringUtils.hasText(sessionInfo.getIfPresent(openId))){
            sessionInfo.put(openId, sessionKey);
            return sessionKey;
        }
        return this.getSessionKey(openId);
    }
}
