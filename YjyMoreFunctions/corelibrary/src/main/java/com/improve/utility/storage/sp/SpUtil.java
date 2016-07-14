package com.improve.utility.storage.sp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Auth：yujunyao
 * Since: 2016/7/13 13:54
 * Email：yujunyao@yonglibao.com
 */
public class SpUtil {

    private static final String KEY_VALUE_STORAGE = "key_value_storage";

    private static SharedPreferences sharedPreference;

    public SpUtil(Context context) {
        if(sharedPreference == null) {
            sharedPreference = context.getSharedPreferences(KEY_VALUE_STORAGE, Activity.MODE_PRIVATE);
        }
    }

    /**获取枚举字段的默认值*/
    private Object getValue(Object keyIndex) {
        DefaultValueInterface defaultEnum = (DefaultValueInterface) keyIndex;
        Object defaultValue = defaultEnum.getValue();
        Class defaultClass = defaultEnum.getClazz();

        if (defaultClass == null) {
            throw new IllegalArgumentException(
                    "the keyValueStorage default class can not be null");
        }

        if (defaultValue != null && defaultClass != defaultValue.getClass()) {
            throw new IllegalArgumentException(
                    "the default value is not the default type's instance");
        }

        return defaultValue;
    }

/****************************************************Long型的存储读取********************************************************/
    public synchronized <T extends Enum> void setLong(T keyIndex, long value) {
        if(!(keyIndex instanceof DefaultValueInterface)) {
            throw new IllegalArgumentException(
                    "the parameters must be a enum implements the defaultValueInterface");
        }

        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putLong(keyIndex.name(), value);
        editor.commit();
    }

    public synchronized <T extends Enum> Long getLong(T keyIndex) {
        if(!(keyIndex instanceof DefaultValueInterface)) {
            throw new IllegalArgumentException(
                    "the parameters must be a enum implements the defaultValueInterface");
        }

        Long value;

        Object caseValue = getValue(keyIndex);
        Long defaultValue = caseValue == null ? 0L : (Long) caseValue;

        if(sharedPreference.contains(keyIndex.name())) {
            value = sharedPreference.getLong(keyIndex.name(), defaultValue);
        }else {
            value = defaultValue;
        }

        return value;
    }

    /****************************************************Boolean型的存储读取********************************************************/
    public synchronized <T extends Enum> void setBoolean(T keyIndex, boolean value) {
        if(!(keyIndex instanceof DefaultValueInterface)) {
            throw new IllegalArgumentException(
                    "the parameters must be a enum implements the defaultValueInterface");
        }

        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putBoolean(keyIndex.name(), value);
        editor.commit();
    }

    public synchronized <T extends Enum> Boolean getBoolean(T keyIndex) {
        if(!(keyIndex instanceof DefaultValueInterface)) {
            throw new IllegalArgumentException(
                    "the parameters must be a enum implements the defaultValueInterface");
        }

        boolean value;

        Object caseValue = getValue(keyIndex);
        Boolean defaultValue = caseValue == null ? false : (Boolean) caseValue;

        if(sharedPreference.contains(keyIndex.name())) {
            value = sharedPreference.getBoolean(keyIndex.name(), defaultValue);
        }else {
            value = defaultValue;
        }

        return value;
    }
    /****************************************************Float型的存储读取********************************************************/
    public synchronized <T extends Enum> void setFloat(T keyIndex, float value) {
        if(!(keyIndex instanceof DefaultValueInterface)) {
            throw new IllegalArgumentException(
                    "the parameters must be a enum implements the defaultValueInterface");
        }

        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putFloat(keyIndex.name(), value);
        editor.commit();
    }

    public synchronized <T extends Enum> Float getFloat(T keyIndex) {
        if(!(keyIndex instanceof DefaultValueInterface)) {
            throw new IllegalArgumentException(
                    "the parameters must be a enum implements the defaultValueInterface");
        }

        Float value;

        Object caseValue = getValue(keyIndex);
        Float defaultValue = caseValue == null ? 0f : (Float) caseValue;

        if(sharedPreference.contains(keyIndex.name())) {
            value = sharedPreference.getFloat(keyIndex.name(), defaultValue);
        }else {
            value = defaultValue;
        }

        return value;
    }
    /****************************************************Int型的存储读取********************************************************/
    public synchronized <T extends Enum> void setInt(T keyIndex, int value) {
        if(!(keyIndex instanceof DefaultValueInterface)) {
            throw new IllegalArgumentException(
                    "the parameters must be a enum implements the defaultValueInterface");
        }

        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putInt(keyIndex.name(), value);
        editor.commit();
    }

    public synchronized <T extends Enum> Integer getInt(T keyIndex) {
        if(!(keyIndex instanceof DefaultValueInterface)) {
            throw new IllegalArgumentException(
                    "the parameters must be a enum implements the defaultValueInterface");
        }

        Integer value;

        Object caseValue = getValue(keyIndex);
        Integer defaultValue = caseValue == null ? 0 : (Integer) caseValue;

        if(sharedPreference.contains(keyIndex.name())) {
            value = sharedPreference.getInt(keyIndex.name(), defaultValue);
        }else {
            value = defaultValue;
        }

        return value;
    }
    /****************************************************String型的存储读取********************************************************/
    public synchronized <T extends Enum> void setString(T keyIndex, String value) {
        if(!(keyIndex instanceof DefaultValueInterface)) {
            throw new IllegalArgumentException(
                    "the parameters must be a enum implements the defaultValueInterface");
        }

        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(keyIndex.name(), value);
        editor.commit();
    }

    public synchronized <T extends Enum> String getString(T keyIndex) {
        if(!(keyIndex instanceof DefaultValueInterface)) {
            throw new IllegalArgumentException(
                    "the parameters must be a enum implements the defaultValueInterface");
        }

        String value;

        Object caseValue = getValue(keyIndex);
        String defaultValue = caseValue == null ? "" : (String) caseValue;

        if(sharedPreference.contains(keyIndex.name())) {
            value = sharedPreference.getString(keyIndex.name(), defaultValue);
        }else {
            value = defaultValue;
        }

        return value;
    }
}
