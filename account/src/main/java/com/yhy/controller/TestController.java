package com.yhy.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("test")
@RefreshScope
public class TestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${config.info:111}")
    private String configInfo;

    @GetMapping("test")
    public String test() {
        try {
            logger.info("test test test");
            logger.warn("warn test test");
            logger.error("error test test");
            if (logger.isDebugEnabled()){
                logger.debug("error test test");
            }
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "test2" + configInfo;
    }
}
