package com.zs.wanandroid.http

import android.util.Log

object HttpLogUtil {

    private val TAG = "OkHttp"

    @JvmStatic
    fun d(msg: String) {
        if (msg.length > 4096) {
            var i = 0
            while (i < msg.length) {
                if (i + 4096 < msg.length)
                    Log.d(
                        TAG,msg.substring(i, i + 4096)
                ) else
                    Log.d(TAG,msg.substring(i, msg.length))
                    i += 4096
            }
        } else {
            Log.d(TAG,msg)
        }
    }
}