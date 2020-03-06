package com.example.zs_wan_android.main

import android.content.Context
import android.os.Bundle
import com.example.zs_wan_android.R
import com.example.zs_wan_android.base.BaseActivity
import com.example.zs_wan_android.base.IBasePresenter
import com.example.zs_wan_android.base.IBaseView

/**
 * activity基础类
 *
 * @author zs
 * @data 2020-03-07
 */
class MainActivity : BaseActivity<IBasePresenter<*>>() ,IBaseView{


    override fun init(savedInstanceState: Bundle?) {

    }

    override fun createPresenter(): IBasePresenter<*>? {
        return MainPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getContext(): Context? {
        return this
    }

    override fun onError(error: String) {

    }


}
