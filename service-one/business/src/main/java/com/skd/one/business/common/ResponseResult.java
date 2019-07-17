package com.skd.one.business.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Describe:
 * @Author: chenfan
 * @Date: 2019/6/25 14:25
 */
@Data
public class ResponseResult implements Serializable {

    private Integer code;
    private Object data;
    private String errorInfo;

}
