package com.example.zs_wan_android.http

import com.orhanobut.logger.LogLevel
import com.orhanobut.logger.Logger

object LogUtil {
    /**
     * 初始化log工具，在app入口处调用
     *
     * @param isLogEnable 是否打印log
     */
    fun init(isLogEnable: Boolean) {
        Logger.init("LogHttpInfo")
            .hideThreadInfo()
            .logLevel(if (isLogEnable) LogLevel.FULL else LogLevel.NONE)
            .methodOffset(2)
    }

    @JvmStatic
    fun d(msg: String) {
        if (msg.length > 4096) {
            var i = 0
            while (i < msg.length) {
                if (i + 4096 < msg.length) Logger.d(
                    msg.substring(
                        i,
                        i + 4096
                    )
                ) else Logger.d(msg.substring(i, msg.length))
                i += 4096
            }
        } else {
            Logger.d(msg)
        }
    }

    fun i(message: String?) {
        Logger.i(message)
    }

    fun w(message: String, e: Throwable?) {
        val info = e?.toString() ?: "null"
        Logger.w("$message：$info")
    }

    fun e(message: String?, e: Throwable?) {
        Logger.e(e, message)
    }

    fun json(json: String?) {
        Logger.json(json)
    }
}