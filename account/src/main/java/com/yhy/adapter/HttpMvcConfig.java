package com.yhy.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

@Component
public class HttpMvcConfig implements WebMvcConfigurer {
    @Autowired
    private TraceIdAdapter TraceIdAdapter;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(TraceIdAdapter);
    }

}
