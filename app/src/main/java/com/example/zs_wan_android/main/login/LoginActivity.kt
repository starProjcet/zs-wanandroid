package com.example.zs_wan_android.main.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.zs_wan_android.R
import com.example.zs_wan_android.base.BaseActivity
import com.example.zs_wan_android.constants.Constants
import com.example.zs_wan_android.main.MainActivity
import com.example.zs_wan_android.utils.AppUtils
import com.example.zs_wan_android.utils.PrefUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginContract.Presenter<LoginContract.View>> (),LoginContract.View{


    override fun init(savedInstanceState: Bundle?) {
        if (AppUtils.isLogin()){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        btLogin.setOnClickListener {
            presenter?.login(etUsername.text.toString(),etPassword.text.toString())
        }
    }


    override fun loginSuccess() {
        PrefUtils.setBoolean(Constants.LOGIN,true)
        startActivity(Intent(this,MainActivity::class.java))
    }

    override fun onError(error: String) {
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
