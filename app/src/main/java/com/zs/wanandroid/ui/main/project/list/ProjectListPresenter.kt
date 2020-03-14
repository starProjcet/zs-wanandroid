package com.zs.wanandroid.ui.main.project.list

import com.zs.wanandroid.base.BasePresenter
import com.zs.wanandroid.entity.ProjectEntity
import com.zs.wanandroid.entity.ProjectListEntity
import com.zs.wanandroid.http.HttpDefaultObserver
import com.zs.wanandroid.http.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author zs
 * @data 2020-03-14
 */
class ProjectListPresenter(view: ProjectListContract.View):BasePresenter<ProjectListContract.View>(view)
    ,ProjectListContract.Presenter<ProjectListContract.View>{

    override fun loadData(id: Int, pageNum: Int) {
        RetrofitHelper.getApiService()
            .getProjectList(pageNum,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<ProjectListEntity>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: ProjectListEntity) {
                    t.datas?.let { view?.showList(it) }
                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)
                }
            })
    }


    /**
     * 取消收藏
     */
    override fun unCollect(id: Int) {
        RetrofitHelper.getApiService()
            .unCollect(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<Any>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: Any) {
                    view?.unCollectSuccess()
                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)
                }

            })
    }

    /**
     * 收藏
     */
    override fun collect(id: Int) {
        RetrofitHelper.getApiService()
            .collect(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<Any>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: Any) {
                    view?.collectSuccess()
                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)
                }

            })
    }

}