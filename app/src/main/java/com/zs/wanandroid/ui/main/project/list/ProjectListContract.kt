package com.zs.wanandroid.ui.main.project.list

import com.zs.wanandroid.base.IBasePresenter
import com.zs.wanandroid.base.IBaseView
import com.zs.wanandroid.entity.ProjectEntity

interface ProjectListContract {
    interface View:IBaseView{
        fun showList(list:MutableList<ProjectEntity>)
        fun collectSuccess()
        fun unCollectSuccess()
    }
    interface Presenter<T>:IBasePresenter<View>{
        fun loadData(id:Int,pageNum:Int)
        fun collect(id:Int)
        fun unCollect(id:Int)
    }
}