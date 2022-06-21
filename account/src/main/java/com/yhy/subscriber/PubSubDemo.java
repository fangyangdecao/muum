package com.yhy.subscriber;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.boot.json.GsonJsonParser;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PubSubDemo
{
    /*private static List<Integer> toastLog = Lists.newCopyOnWriteArrayList();
    public static void main( String[] args ) throws InterruptedException {
        Color bg = Color.decode("#C6C6C6");
        Color bg2 = Color.decode("#FFFFFF");
        System.out.println(bg);
        System.out.println(bg2);
*/

/*        ExecutorService executor = Executors.newFixedThreadPool(100);
        for (int i = 10000; i > 0; i--) {
            executor.submit(()->{
                toastLog.add(1);
            });
        }
        Thread.sleep(10000);
        System.out.println(toastLog.size());*/


/*        // 替换成你的reids地址和端口
        String redisIp = "127.0.0.1";
        int reidsPort = 6379;
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), redisIp, reidsPort);
        System.out.println(String.format("redis pool is starting, redis ip %s, redis port %d", redisIp, reidsPort));
        try (Jedis resource = jedisPool.getResource()){

            resource.auth("root");
            Map<String, String> map = new HashMap<>();
            map.put("a","1");
            map.put("b","2");
            resource.hmset("test:test1",map);

            Map<String, String> map2 = new HashMap<>();
            map2.put("a","1");
            map2.put("b","2");
            resource.hmset("test:test2",map2);

            Map<String, String> test = resource.hgetAll("test");
            System.out.println(test);
            Set<String> test1 = resource.hkeys("test");
            System.out.println(test1);
        }*/

        //SubThread subThread = new SubThread(jedisPool);
        //subThread.start();

        //Publisher publisher = new Publisher(jedisPool);
        //publisher.start();
    //}
}
