package com.example.zs_wan_android.main.home

import com.example.zs_wan_android.base.IBasePresenter
import com.example.zs_wan_android.base.IBaseView
import com.example.zs_wan_android.entity.BannerEntity
import com.example.zs_wan_android.entity.HomeEntity

interface HomeContract {
    interface View : IBaseView {
        fun showList(list: MutableList<HomeEntity.DatasBean>)
        fun showBanner(bannerList:MutableList<BannerEntity>)
    }

    interface Presenter<T> : IBasePresenter<View> {
        fun loadData(pageNum:Int)
        fun loadBanner()
    }
}