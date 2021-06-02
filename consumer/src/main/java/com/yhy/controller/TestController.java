package com.yhy.controller;

import com.yhy.fergin_rpc.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consumer")
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TestService testService;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @GetMapping("test")
    public String test(){
        //return restTemplate.getForObject("http://account/test/test",String.class);
        return testService.test();
    }
}
