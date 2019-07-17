package com.skd.one.api.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Describe:
 * @Author: chenfan
 * @Date: 2019/6/25 13:29
 */
@FeignClient("service-one")
public interface DemoOneService {

    @RequestMapping(value = "/api/v1/one/ping",method = RequestMethod.GET)
    String ping();

    @RequestMapping(value = "/api/v1/one/hello",method = RequestMethod.POST)
    String hello();

    @RequestMapping(value = "/api/v1/one/real/ping",method = RequestMethod.GET)
    String realPing();

    @RequestMapping(value = "/api/v1/one/real/hello",method = RequestMethod.POST)
    String realHello();
}
