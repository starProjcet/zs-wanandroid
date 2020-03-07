package com.example.zs_wan_android.main.home

import android.util.Log
import com.example.zs_wan_android.base.BasePresenter
import com.example.zs_wan_android.http.RetrofitUtil
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter(view:HomeContract.View):BasePresenter<HomeContract.View>(view) ,HomeContract.Presenter<HomeContract.View>{

    override fun loadData() {
        val disposable = RetrofitUtil.getApiService()
            .getHomeList(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            })
        addSubscribe(disposable)
    }

}