package com.example.zs_wan_android.base

import android.content.Context


interface IBaseView {

    fun getContext():Context?
    fun onError(error:String)
}