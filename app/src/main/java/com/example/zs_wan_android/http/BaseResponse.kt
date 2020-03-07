package com.example.zs_wan_android.http

class BaseResponse<T> {
    var data: T? = null
    var errorMsg: String? = null
    var errorCode = 0


}