package com.example.zs_wan_android.http

import com.google.gson.JsonParseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


abstract class HttpDefaultObserver<T> :Observer<T> {

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        var errorMsg = ""
        if (e is UnknownHostException) {
        } else if (e is JSONException || e is JsonParseException) {
            errorMsg = "数据异常"
        } else if (e is SocketTimeoutException) {
            errorMsg = "连接超时"
        } else if (e is ConnectException) {
            errorMsg = "连接错误"
        } else if (e is BusinessHttpException){
            errorMsg = e.businessMessage
        } else{
            errorMsg = "未知错误"
        }
        onError(errorMsg)
    }


    abstract fun onSuccess(t:T)
    abstract fun onError(errorMsg:String)


}