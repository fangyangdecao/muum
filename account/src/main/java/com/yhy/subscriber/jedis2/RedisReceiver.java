package com.yhy.subscriber.jedis2;

import org.springframework.stereotype.Component;

@Component
public class RedisReceiver {
    /**
     * 这里是收到通道的消息之后执行的方法
     *
     * @param message
     */
    public void receiveMessage(String message) {
    try {
        Thread.sleep(5000);
        System.out.println("消息来了：" + message);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
}