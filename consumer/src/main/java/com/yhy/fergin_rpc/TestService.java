package com.yhy.fergin_rpc;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "account")
@Component
public interface TestService {

    @RequestMapping(value = "/test/test",method = RequestMethod.GET)
    String test();
}
