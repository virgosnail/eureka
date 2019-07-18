package com.skd.two.business.controller;

import com.skd.one.api.service.DemoOneService;
import com.skd.two.api.service.DemoTwoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Describe:
 * @Author: chenfan
 * @Date: 2019/6/25 13:25
 */
@Slf4j
@Api(value = "DemoService")
@RestController
@RequestMapping(value = "/api/v1/two", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestTwoController implements DemoTwoService {


    @Autowired
    private DemoOneService demoOneService;

    @Override
    @RequestMapping(name = "/ping", method = RequestMethod.GET)
    @ApiOperation(value = "ping")
    public String ping() {
        log.info(" service two ping");
        String realPing = demoOneService.realPing();
        return realPing;
    }

    @Override
    @RequestMapping(name = "/hello", method = RequestMethod.POST)
    @ApiOperation(value = "hello")
    public String hello() {
        log.info(" service two hello");
        String realHello = demoOneService.realHello();
        return realHello;
    }

    @Override
    @RequestMapping(value = "/real/ping",method = RequestMethod.GET)
    public String realPing() {
        return "Demo two Service pong";
    }

    @Override
    @RequestMapping(value = "/real/hello",method = RequestMethod.POST)
    public String realHello() {
        return "Demo two Service hello";
    }
}
