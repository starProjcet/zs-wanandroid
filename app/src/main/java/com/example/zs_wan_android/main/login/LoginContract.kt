package com.example.zs_wan_android.main.login

import com.example.zs_wan_android.base.IBasePresenter
import com.example.zs_wan_android.base.IBaseView

interface LoginContract {
    interface View :IBaseView{
        fun loginSuccess()
    }
    interface Presenter<T>:IBasePresenter<View>{
        fun login(username:String,password:String)
    }
}