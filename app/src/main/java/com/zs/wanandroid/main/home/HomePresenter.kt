package com.zs.wanandroid.main.home

import com.zs.wanandroid.base.BasePresenter
import com.zs.wanandroid.entity.BannerEntity
import com.zs.wanandroid.entity.HomeEntity
import com.zs.wanandroid.http.HttpDefaultObserver
import com.zs.wanandroid.http.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author zs
 * @data 2020-03-08
 */
class HomePresenter(view: HomeContract.View):
    BasePresenter<HomeContract.View>(view) ,
    HomeContract.Presenter<HomeContract.View> {

    /**
     * 加载首页文章列表
     */
    override fun loadData(pageNum:Int) {
        com.zs.wanandroid.http.RetrofitHelper.getApiService()
            .getHomeList(pageNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<HomeEntity>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: HomeEntity) {
                    if (pageNum==0){
                        t.datas?.let { loadTopList(it) }
                    }else{
                        t.datas?.let { view?.showList(it) }
                    }
                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)
                }

            })
    }
    /**
     * 包括置顶文章
     */
    private fun loadTopList(list:MutableList<HomeEntity.DatasBean>){
        com.zs.wanandroid.http.RetrofitHelper.getApiService()
            .getTopList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<MutableList<HomeEntity.DatasBean>>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                //获取置顶文章成功加入到文章列表头部
                override fun onSuccess(top: MutableList<HomeEntity.DatasBean>) {
                    list.addAll(0,top)
                    view?.showList(list)
                }

                //获取置顶文章失败直接返回文章列表
                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)
                    view?.showList(list)
                }
            })
    }

    /**
     * 由于banner和list位于同一界面
     * 所以banner在presenter内部请求
     */
    override fun loadBanner(){
        com.zs.wanandroid.http.RetrofitHelper.getApiService()
            .getBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<MutableList<BannerEntity>>() {
                override fun onSuccess(t: MutableList<BannerEntity>) {
                    view?.showBanner(t)
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