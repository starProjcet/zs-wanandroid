package com.zs.wanandroid.ui.main.project

import com.zs.wanandroid.base.IBasePresenter
import com.zs.wanandroid.base.IBaseView
import com.zs.wanandroid.entity.TabEntity

/**
 * @author zs
 * @data 2020-03-14
 */
interface OfficialAccountContract {
    interface View:IBaseView{
        fun showList(list:MutableList<TabEntity>)
    }

    interface Presenter<T>:IBasePresenter<View>{
        fun loadData()
    }
}