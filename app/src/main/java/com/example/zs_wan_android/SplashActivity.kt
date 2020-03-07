package com.example.zs_wan_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zs_wan_android.base.BaseActivity
import com.example.zs_wan_android.base.IBasePresenter

/**
 * 开屏页
 *
 * @author zs
 * @data 2020-03-07
 */
class SplashActivity : BaseActivity<IBasePresenter<*>>() {


    override fun init(savedInstanceState: Bundle?) {
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }
}
