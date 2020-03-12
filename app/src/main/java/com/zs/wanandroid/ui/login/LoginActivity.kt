package com.zs.wanandroid.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.zs_wan_android.R
import com.zs.wanandroid.base.BaseActivity
import com.zs.wanandroid.constants.Constants
import com.zs.wanandroid.ui.main.MainActivity
import com.zs.wanandroid.utils.AppManager
import com.zs.wanandroid.utils.PrefUtils
import com.zs.wanandroid.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginContract.Presenter<LoginContract.View>>(),
    LoginContract.View {


    override fun init(savedInstanceState: Bundle?) {
        if (AppManager.isLogin()){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        btLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            when {
                username.isEmpty() -> ToastUtils.show("请输入账号")
                password.isEmpty() -> ToastUtils.show("请输入密码")
                else -> presenter?.login(username,password)
            }
        }
        tvRegister.setOnClickListener {

        }
        tvSkip.setOnClickListener {
            finish()
        }
    }

    override fun loginSuccess() {
        PrefUtils.setBoolean(Constants.LOGIN,true)
        finish()
    }

    override fun onError(error: String) {
        ToastUtils.show(error)
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun createPresenter(): LoginContract.Presenter<LoginContract.View>? {
        return LoginPresenter(this)
    }
    override fun getContext(): Context? {
        return this
    }

}
