package com.skd.two.business.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skd.two.business.common.GlobalException;
import com.skd.two.business.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
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
@Slf4j
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
            result.setCode(200);
            result.setData("");
        } else if (body instanceof GlobalException){
            result.setCode(((GlobalException) body).getErrorCode());
            result.setErrorInfo(((GlobalException) body).getErrorInfo());
        }else if (body instanceof ResponseResult){
            result = (ResponseResult)body;
        } else {
            result.setCode(200);
            result.setData(body);
        }

        try {
            // 返回为 String 时,后续可能会对返回结果进行进一步处理,所以此处先将结果使用 Json 序列化为 String
            serverHttpResponse.getHeaders().add("content-type", "application/json;charset=UTF-8");
            return String.class == body.getClass()? objectMapper.writeValueAsString(result):result;
        } catch (JsonProcessingException e) {
            log.error("beforeBodyWrite occur a exception:{}",e);
            result.setCode(300);
            result.setErrorInfo("beforeBodyWrite occur a exception:" + e.toString());
            return result;
        }
    }
}
