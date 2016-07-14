package com.yjymorefunctions.utils;

import com.improve.utility.storage.sp.DefaultValueInterface;

/**
 * Auth：yujunyao
 * Since: 2016/7/13 15:27
 * Email：yujunyao@yonglibao.com
 */
public enum SpKey implements DefaultValueInterface {
    TEST_BOOLEAN(true, Boolean.class),

    TEST_LONG(0L, Long.class),

    TEST_FLOAT(0f, Float.class),

    TEST_INT(0, Integer.class),

    TEST_STRING("", String.class)
    ;


    private Object defaultValue;
    private Class defaultClazz;

    SpKey(Object defaultValue, Class defaultClazz) {
        this.defaultValue = defaultValue;
        this.defaultClazz = defaultClazz;
    }

    @Override
    public Object getValue() {
        return defaultValue;
    }

    @Override
    public Class getClazz() {
        return defaultClazz;
    }
}
