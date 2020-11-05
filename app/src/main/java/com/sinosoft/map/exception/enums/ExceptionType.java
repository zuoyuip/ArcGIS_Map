package com.sinosoft.map.exception.enums;

import lombok.Getter;

/**
 * 异常类型 .
 *
 * @author: zuoyu
 * @create: 2020-11-04 10:49
 */
@Getter
public enum ExceptionType {

    MEDIA_NOT_MOUNTED("存储设备未挂载"),

    PATH_NOT_EXISTS("路径不存在"),

    NOT_CAN_MKDIR("无法创建目录"),

    NOT_CAN_CREATE_OBJ("无法创建对象");

    private final String message;

    ExceptionType(String message) {
        this.message = message;
    }
}
