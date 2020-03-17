package com.zs.wanandroid.ui.main.account

import com.zs.wanandroid.base.BasePresenter
import com.zs.wanandroid.entity.TabEntity
import com.zs.wanandroid.http.HttpDefaultObserver
import com.zs.wanandroid.http.RetrofitHelper
import com.zs.wanandroid.ui.main.project.OfficialAccountContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author zs
 * @data 2020-03-14
 */
class OfficialAccountPresenter(view: OfficialAccountContract.View):BasePresenter<OfficialAccountContract.View>(view)
    , OfficialAccountContract.Presenter<OfficialAccountContract.View>{

    override fun loadData() {
        RetrofitHelper.getApiService()
            .getAccountTabList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :HttpDefaultObserver<MutableList<TabEntity>>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: MutableList<TabEntity>) {
                    view?.showList(t)
                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)
                }
            })
    }

}