package com.example.zs_wan_android.main.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.zs_wan_android.R
import com.example.zs_wan_android.base.BaseActivity
import com.example.zs_wan_android.constants.Constants
import com.example.zs_wan_android.main.MainActivity
import com.example.zs_wan_android.utils.AppUtils
import com.example.zs_wan_android.utils.PrefUtils
import com.example.zs_wan_android.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginContract.Presenter<LoginContract.View>> (),LoginContract.View{


    override fun init(savedInstanceState: Bundle?) {
        if (AppUtils.isLogin()){
            startActivity(Intent(this,MainActivity::class.java))
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
    }


    override fun loginSuccess() {
        PrefUtils.setBoolean(Constants.LOGIN,true)
        startActivity(Intent(this,MainActivity::class.java))
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

    var lastTime:Long = 0
    override fun onBackPressed() {
        if (System.currentTimeMillis() - this.lastTime > 2000L) {
            ToastUtils.show("再按一次退出程序")
            this.lastTime = System.currentTimeMillis()
            return
        } else {
            super.onBackPressed()
        }
    }
}
