package com.zs.wanandroid.main.set

import com.zs.wanandroid.base.BasePresenter
import com.zs.wanandroid.http.HttpDefaultObserver
import com.zs.wanandroid.http.RetrofitHelper
import com.zs.wanandroid.utils.AppUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SetPresenter(view:SetContract.View)
    :BasePresenter<SetContract.View>(view),SetContract.Presenter<SetContract.View> {

    override fun logout() {
        RetrofitHelper.getApiService()
            .logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<Any>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }
                override fun onSuccess(t: Any) {
                }
                override fun onError(errorMsg: String) {
                }
            })
    }
}