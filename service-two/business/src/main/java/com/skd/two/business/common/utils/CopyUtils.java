package com.skd.two.business.common.utils;

import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

/**
 * @Describe:
 * @Author: chenfan
 * @Date: 2019/6/28 14:21
 */
public class CopyUtils {

    /**
     * 获取空值字段
     *
     * @param source 对象
     * @return 空值字段数组
     */
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapperImpl src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        return Arrays.stream(pds)
                .filter(property -> null == src.getPropertyValue(property.getName()))
                .map(PropertyDescriptor::getName)
                .distinct()
                .toArray(String[]::new);
    }
}
