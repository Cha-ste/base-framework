package com.ocean.exception;

/**
 * 数据库查询不到任何记录异常
 */
public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException() {
        super("数据不存在");
    }
}
