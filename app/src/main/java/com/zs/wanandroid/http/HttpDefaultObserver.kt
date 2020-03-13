package com.zs.wanandroid.http

import com.google.gson.JsonParseException
import com.zs.wanandroid.utils.AppManager
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.lang.reflect.ParameterizedType


/**
 * des 给Response脱壳,对服务器错误统一处理
 *
 * @author zs
 * @data 2020-03-13
 */
abstract class HttpDefaultObserver<T> :Observer<BaseResponse<T>> {

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        disposable(d)
    }

    override fun onNext(t: BaseResponse<T>) {
        if (t.errorCode==0) {

            if (t.data==null){
                val tClass =
                    (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
                t.data = tClass.newInstance()
            }
            onSuccess(t.data)
        }
        //code!=0代表业务出错，进行过滤
        else{
            filterCode(t.errorMsg,t.errorCode)
        }
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

    private fun filterCode(msg: String, code: Int) {
        when (code) {
            //登录失败
            -1001 -> {
                AppManager.resetUser()
                onError(BusinessHttpException(msg, code))
            }
            //未知code,将errorMsg封装成异常,由onError()处理
            else -> onError(BusinessHttpException(msg, code))
        }
    }

    abstract fun disposable(d: Disposable)
    abstract fun onSuccess(t:T?)
    abstract fun onError(errorMsg:String)

}