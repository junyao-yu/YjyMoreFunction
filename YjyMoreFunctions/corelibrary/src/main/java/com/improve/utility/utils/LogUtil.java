package com.improve.utility.utils;

import com.orhanobut.logger.Logger;

/**
 * desc:打印日志工具类， 打 release包改为DEBUG=false
 */
public class LogUtil {
    public static final boolean DEBUG = true;

    public static void i(String tag, String msg) {
        if (DEBUG)
            Logger.t(tag).i(msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG)
            Logger.t(tag).e(msg);
    }

    public static void v(String tag, String msg) {
        if (DEBUG)
            Logger.t(tag).v(msg);
    }

    public static void d(String tag, String msg) {
        if (DEBUG)
            Logger.t(tag).d(msg);
    }

    /**
     * 打印完整json
     * @param tag
     * @param msg
     */
    public static void json(String tag , String msg){
        if(DEBUG)
            Logger.t(tag).json(msg);
    }

    /**
     * 打印xml
     * @param tag
     * @param msg
     */
    public static void xml(String tag , String msg){
        if(DEBUG)
            Logger.t(tag).xml(msg);
    }

}
