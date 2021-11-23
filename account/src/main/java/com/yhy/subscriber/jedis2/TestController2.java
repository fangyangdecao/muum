package com.yhy.subscriber.jedis2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@EnableScheduling
@Component
public class TestController2 {
    private AtomicInteger a  = new AtomicInteger(0);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 向redis消息队列test通道发布消息
     */
    //@Scheduled(fixedRate = 10000)
    public void sendMessage() {
        for (int i = 0; i < 100; i++) {
            CompletableFuture.runAsync(()->{
                int andIncrement = a.getAndIncrement();
                System.out.println(andIncrement);
                stringRedisTemplate.convertAndSend("test", Thread.currentThread() + String.valueOf(andIncrement));
            });
        }
    }
}