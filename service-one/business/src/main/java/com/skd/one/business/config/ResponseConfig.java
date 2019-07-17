package com.skd.one.business.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skd.one.business.common.GlobalException;
import com.skd.one.business.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Describe:
 * @Author: chenfan
 * @Date: 2019/6/25 15:07
 */
@Configuration
@RestControllerAdvice
public class ResponseConfig implements ResponseBodyAdvice<Object> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return null != methodParameter.getMethod() && ResponseEntity.class != methodParameter.getMethod().getReturnType();
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ResponseResult result = new ResponseResult();
        if (null == body){
            result.setCode(HttpStatus.OK.value());
            result.setData("");
        } else if (body instanceof ResponseResult){
            result = (ResponseResult) body;
        } else if (body instanceof GlobalException){
            result.setCode(((GlobalException) body).getErrorCode());
            result.setErrorInfo(((GlobalException) body).getErrorInfo());
        } else {
            result.setCode(HttpStatus.OK.value());
//            result.setData(objectMapper.writeValueAsString(body));
        }


        return result;
    }
}
