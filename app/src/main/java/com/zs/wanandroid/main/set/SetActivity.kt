package com.zs.wanandroid.main.set

import android.content.Context
import android.os.Bundle
import com.example.zs_wan_android.R
import com.zs.wanandroid.base.BaseActivity
import com.zs.wanandroid.main.login.LoginActivity
import com.zs.wanandroid.utils.AppUtils
import com.zs.wanandroid.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_set.*

class SetActivity : BaseActivity<SetContract.Presenter<SetContract.View>>(),SetContract.View {



    override fun init(savedInstanceState: Bundle?) {
        /**
         * 因为后端不记录登陆状态，所以此处制作模拟退出
         */
        tvLogout.setOnClickListener {
            presenter?.logout()
            ToastUtils.show("已退出登录")
            AppUtils.resetUser()
            intent(LoginActivity::class.java,false)
        }
    }

    override fun createPresenter(): SetContract.Presenter<SetContract.View>? {
        return SetPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_set
    }

    override fun logoutSuccess() {
    }

    override fun getContext(): Context? {
        return this
    }

    override fun onError(error: String) {
        ToastUtils.show(error)
    }

}
