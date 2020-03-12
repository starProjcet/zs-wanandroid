package com.zs.wanandroid.proxy

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import com.example.zs_wan_android.R

/**
 * des 对话框代理类，对外暴露使用接口，将框架与业务进行隔离
 * @author zs
 * @data 2020-03-12
 */
class DialogProxy {

    companion object{
        /**
         * 二次确认对话框
         */
        fun confirm(context: Context,tips:String,callBack:IConfirmClickCallBack?){
            MaterialDialog(context).show {
                cornerRadius(8f)
                title(text = "提示")
                message(text = tips)
                positiveButton(text = "确定") {
                    callBack?.onClick()
                }
                negativeButton(text = "取消")
            }
        }
    }
}