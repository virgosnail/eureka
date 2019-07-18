package com.skd.two.business.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Describe:
 * @Author: chenfan
 * @Date: 2019/6/25 14:25
 */
@Data
public class ResponseResult <D> implements Serializable {

    private Integer code;
    private D data;
    private String errorInfo;

}
