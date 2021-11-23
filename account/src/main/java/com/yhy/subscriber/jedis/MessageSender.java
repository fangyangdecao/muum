package com.yhy.subscriber.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@EnableScheduling//开启定时器功能
//@Component
public class MessageSender {
    //@Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 间隔2秒，通过stringRedisTemplate对象向redis消息队列chat频道发布消息
     */
    //@Scheduled(fixedDelay = 2000)
    public void sendMessage() {
        stringRedisTemplate.convertAndSend("chat", String.valueOf(Math.random()));
    }
}

