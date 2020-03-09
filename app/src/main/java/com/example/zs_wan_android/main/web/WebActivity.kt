package com.example.zs_wan_android.main.web

import android.os.Bundle
import com.example.zs_wan_android.R
import com.example.zs_wan_android.base.BaseActivity
import com.example.zs_wan_android.base.IBasePresenter

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
