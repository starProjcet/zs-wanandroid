package com.example.zs_wan_android.base

import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.android.ActivityEvent

open class BasePresenter<V:IBaseView>(view: V) :IBasePresenter<V>{

    protected var view:V? = view
    protected var lifecycleProvider:LifecycleProvider<ActivityEvent>? = null

    override fun bindLifecycle(lifecycleProvider:LifecycleProvider<ActivityEvent>){
        this.lifecycleProvider = lifecycleProvider
    }

    override fun onCreate() {

    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onDestroy() {
    }

}