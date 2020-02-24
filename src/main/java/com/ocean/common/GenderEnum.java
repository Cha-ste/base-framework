package com.ocean.common;

/**
 * 性别枚举类
 */
public enum GenderEnum {
    MALE("男"),
    FEMALE("女");

    private String name;

    GenderEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
