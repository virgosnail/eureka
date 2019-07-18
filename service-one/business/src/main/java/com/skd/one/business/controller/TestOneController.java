package com.skd.one.business.controller;

import com.skd.one.api.service.DemoOneService;
import com.skd.two.api.service.DemoTwoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Describe:
 * @Author: chenfan
 * @Date: 2019/6/25 13:25
 */
@Slf4j
@Api(value = "DemoService")
@RestController
@RequestMapping(value = "/api/v1/one", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestOneController implements DemoOneService {


    @Autowired
    private DemoTwoService demoTwoService;

    @Override
    @RequestMapping(name = "/ping", method = RequestMethod.GET)
    @ApiOperation(value = "ping")
    public String ping() {
        log.info(" service one ping");
        String realPing = demoTwoService.realPing();
        return realPing;
    }

    @Override
    @RequestMapping(name = "/hello", method = RequestMethod.POST)
    @ApiOperation(value = "hello")
    public String hello() {

        String realHello = demoTwoService.realHello();
        return realHello;
    }

    @Override
    @RequestMapping(value = "/real/ping",method = RequestMethod.GET)
    public String realPing() {
        return "Demo one Service pong";
    }

    @Override
    @RequestMapping(value = "/real/hello",method = RequestMethod.POST)
    public String realHello() {
        return "Demo one Service hello";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(@RequestParam String a,@RequestParam(required = false) int b) {
        return a + b;
    }
}
