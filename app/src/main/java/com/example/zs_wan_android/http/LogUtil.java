package com.example.zs_wan_android.http;

import android.util.Log;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

public class LogUtil {

    /**
     * 初始化log工具，在app入口处调用
     *
     * @param isLogEnable 是否打印log
     */
    public static void init(boolean isLogEnable) {
        Logger.init("LogHttpInfo")
                .hideThreadInfo()
                .logLevel(isLogEnable ? LogLevel.FULL : LogLevel.NONE)
                .methodOffset(2);
    }

    public static void d(String msg) {
        if (msg.length() > 4096) {
            for (int i = 0; i < msg.length(); i += 4096) {
                if (i + 4096 < msg.length())
                    Logger.d(msg.substring(i, i + 4096));
                else
                    Logger.d(msg.substring(i, msg.length()));
            }
        } else {
            Logger.d(msg);
        }
    }

    public static void i(String message) {
        Logger.i(message);
    }

    public static void w(String message, Throwable e) {
        String info = e != null ? e.toString() : "null";
        Logger.w(message + "：" + info);
    }

    public static void e(String message, Throwable e) {
        Logger.e(e, message);
    }

    public static void json(String json) {
        Logger.json(json);
    }
}
