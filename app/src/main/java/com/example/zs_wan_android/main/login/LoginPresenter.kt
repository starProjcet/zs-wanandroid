package com.example.zs_wan_android.main.login

import com.example.zs_wan_android.base.BasePresenter
import com.example.zs_wan_android.entity.LoginEntity
import com.example.zs_wan_android.http.HttpDefaultObserver
import com.example.zs_wan_android.http.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginPresenter(view:LoginContract.View):BasePresenter<LoginContract.View>(view)
    ,LoginContract.Presenter<LoginContract.View>{


    override fun login(username: String, password: String) {
        RetrofitHelper.getApiService()
            .login(username,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<LoginEntity>() {
                override fun onSuccess(t: LoginEntity) {
                    view?.loginSuccess()
                }
                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)
                }

                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }
            })
    }


}
