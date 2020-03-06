package com.example.zs_wan_android.base

import android.content.Context
import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.LifecycleTransformer


interface IBaseView {
//    fun <T> bindLifecycle(): LifecycleProvider<T>
    fun getContext():Context?
    fun onError(error:String)
}