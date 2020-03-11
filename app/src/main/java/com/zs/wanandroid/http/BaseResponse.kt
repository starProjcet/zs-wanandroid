package com.zs.wanandroid.http

class BaseResponse<T> {
    var data: T? = null
    var errorMsg: String? = null
    var errorCode = 0


}