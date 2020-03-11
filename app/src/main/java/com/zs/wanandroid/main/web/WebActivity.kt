package com.zs.wanandroid.main.web

import android.os.Bundle
import com.example.zs_wan_android.R
import com.zs.wanandroid.base.BaseActivity
import com.zs.wanandroid.base.IBasePresenter

class WebActivity : BaseActivity<IBasePresenter<*>>() {


    override fun init(savedInstanceState: Bundle?) {
    }


    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_web
    }


}
