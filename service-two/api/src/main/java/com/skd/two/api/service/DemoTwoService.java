package com.skd.two.api.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Describe:
 * @Author: chenfan
 * @Date: 2019/6/25 13:29
 */
@FeignClient("service-two")
public interface DemoTwoService {

    @RequestMapping(value = "/api/v1/two/ping",method = RequestMethod.GET)
    String ping();

    @RequestMapping(value = "/api/v1/two/hello",method = RequestMethod.POST)
    String hello();

    @RequestMapping(value = "/api/v1/two/real/ping",method = RequestMethod.GET)
    String realPing();

    @RequestMapping(value = "/api/v1/two/real/hello",method = RequestMethod.POST)
    String realHello();
}
