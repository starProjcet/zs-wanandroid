package com.zs.wanandroid.ui.main.project

import com.zs.wanandroid.base.BasePresenter
import com.zs.wanandroid.entity.ProjectTabEntity
import com.zs.wanandroid.http.HttpDefaultObserver
import com.zs.wanandroid.http.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author zs
 * @data 2020-03-14
 */
class ProjectPresenter(view:ProjectContract.View):BasePresenter<ProjectContract.View>(view)
    , ProjectContract.Presenter<ProjectContract.View>{

    override fun loadData() {
        RetrofitHelper.getApiService()
            .getProjectTabList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :HttpDefaultObserver<MutableList<ProjectTabEntity>>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: MutableList<ProjectTabEntity>) {
                    view?.showList(t)
                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)
                }
            })
    }

}