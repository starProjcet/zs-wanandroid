package com.zs.wanandroid.ui.main.project

import com.zs.wanandroid.base.IBasePresenter
import com.zs.wanandroid.base.IBaseView
import com.zs.wanandroid.entity.ProjectTabEntity

/**
 * @author zs
 * @data 2020-03-14
 */
interface ProjectContract {
    interface View:IBaseView{
        fun showList(list:MutableList<ProjectTabEntity>)
    }

    interface Presenter<T>:IBasePresenter<View>{
        fun loadData()
    }
}