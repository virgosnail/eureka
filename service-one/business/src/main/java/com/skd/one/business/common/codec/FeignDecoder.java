package com.skd.one.business.common.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.skd.one.business.common.GlobalException;
import com.skd.one.business.common.ResponseResult;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.json.Jackson2JsonDecoder;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @Description:
 * @Author: virgosnail
 * @Date: 2019/7/14 18:10
 */
@Configuration
@Slf4j
public class FeignDecoder implements Decoder {

    @Autowired
    private static ObjectMapper objectMapper;

    private static Gson gson = new Gson();



    /**
     * Decodes an http response into an object corresponding to its
     * {@link Method#getGenericReturnType() generic return type}. If you need to
     * wrap exceptions, please do so via {@link DecodeException}.
     *
     * @param response the response to decode
     * @param type     {@link Method#getGenericReturnType() generic return type} of the
     *                 method corresponding to this {@code response}.
     * @return instance of {@code type}
     * @throws IOException     will be propagated safely to the caller.
     * @throws DecodeException when decoding failed due to a checked exception besides IOException.
     * @throws FeignException  when decoding succeeds, but conveys the operation failed.
     */
    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        log.info("response:[{}], type:[{}]",response,type);
        Reader reader = response.body().asReader();
        ResponseResult result = gson.fromJson(reader, ResponseResult.class);
        Integer code = result.getCode();
        boolean success = HttpStatus.OK.value() <= code && HttpStatus.BAD_REQUEST.value() > code;
        if (success){
            return result.getData();
        } else {
            GlobalException e = new GlobalException();
            e.setErrorCode(result.getCode());
            e.setErrorInfo(result.getErrorInfo());
            throw e;
        }
    }
}
