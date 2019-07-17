package com.skd.one.business.common;


import com.skd.one.business.common.GlobalException;
import com.skd.one.business.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.ObjectError;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @program: dtms
 * @description: 全局异常处理
 * @author: YangYang ST02002
 * @create: 2018-11-07
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final ObjectError UNKNOWN_PARAM_ERROR = new ObjectError("unknown", "未知参数错误！");

    /**
     * 系统内部异常
     *
     * @param e        异常对象
     * @param request  请求对象
     * @param response 返回信息
     * @return 返回异常信息
     */
    @ExceptionHandler(Exception.class)
    public GlobalException handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {

        GlobalException exception = new GlobalException();
        exception.setErrorCode(500);
        exception.setErrorInfo(e.toString());
        return exception;
    }

}
