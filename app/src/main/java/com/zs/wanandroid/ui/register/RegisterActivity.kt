package com.zs.wanandroid.ui.register

import android.content.Context
import android.os.Bundle
import com.example.zs_wan_android.R
import com.zs.wanandroid.base.BaseActivity
import com.zs.wanandroid.utils.ToastUtils


/**
 * @author zs
 * @date
 */
class RegisterActivity : BaseActivity<RegisterContract.Presenter<RegisterContract.View>>(),RegisterContract.View {
    override fun init(savedInstanceState: Bundle?) {

    }

    override fun registerSuccess() {
    }


    override fun onError(error: String) {
        ToastUtils.show(error)
    }


    override fun createPresenter(): RegisterContract.Presenter<RegisterContract.View>? {
        return RegisterPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun getContext(): Context? {
        return this
    }


}
