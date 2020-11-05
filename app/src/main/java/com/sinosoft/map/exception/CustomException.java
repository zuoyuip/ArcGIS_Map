package com.sinosoft.map.exception;

import com.sinosoft.map.exception.enums.ExceptionType;

import androidx.annotation.NonNull;

/**
 * 自定义异常 .
 *
 * @author: zuoyu
 * @create: 2020-11-04 10:46
 */
public class CustomException extends RuntimeException{

    public CustomException(@NonNull ExceptionType exceptionType) {
        super(exceptionType.getMessage());
    }
}
