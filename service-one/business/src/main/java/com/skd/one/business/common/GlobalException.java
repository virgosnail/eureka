package com.skd.one.business.common;

import lombok.Data;

/**
 * @Description: 全局异常对象，将 controller 抛出的异常统一封装处理，方便对请求结果的统一处理
 * @Author: virgosnail
 * @Date: 2019/7/14 17:48
 */
@Data
public class GlobalException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误信息
     */
    private String errorInfo;



}
