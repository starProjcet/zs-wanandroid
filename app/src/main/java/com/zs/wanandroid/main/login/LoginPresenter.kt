package com.zs.wanandroid.main.login

import com.zs.wanandroid.base.BasePresenter
import com.zs.wanandroid.entity.LoginEntity
import com.zs.wanandroid.http.HttpDefaultObserver
import com.zs.wanandroid.http.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginPresenter(view: LoginContract.View):
    BasePresenter<LoginContract.View>(view)
    ,
    LoginContract.Presenter<LoginContract.View> {


    override fun login(username: String, password: String) {
        com.zs.wanandroid.http.RetrofitHelper.getApiService()
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
