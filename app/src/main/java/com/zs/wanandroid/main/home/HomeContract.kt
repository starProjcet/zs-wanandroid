package com.zs.wanandroid.main.home

import com.zs.wanandroid.base.IBasePresenter
import com.zs.wanandroid.base.IBaseView
import com.zs.wanandroid.entity.BannerEntity
import com.zs.wanandroid.entity.HomeEntity

interface HomeContract {
    interface View : IBaseView {
        fun showList(list: MutableList<HomeEntity.DatasBean>)
        fun showBanner(bannerList:MutableList<BannerEntity>)
    }

    interface Presenter<T> :
        IBasePresenter<View> {
        fun loadData(pageNum:Int)
        fun loadBanner()
    }
}