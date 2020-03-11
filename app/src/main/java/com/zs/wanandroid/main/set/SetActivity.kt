package com.zs.wanandroid.main.set

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.zs_wan_android.R
import com.zs.wanandroid.base.BaseActivity
import com.zs.wanandroid.main.login.LoginActivity
import com.zs.wanandroid.utils.AppUtils
import com.zs.wanandroid.utils.StatusUtils
import com.zs.wanandroid.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_set.*

class SetActivity : BaseActivity<SetContract.Presenter<SetContract.View>>(),SetContract.View ,
    View.OnClickListener {


    override fun init(savedInstanceState: Bundle?) {
        /**
         * 因为后端不记录登陆状态，所以此处制作模拟退出
         */
        ivBack.setOnClickListener(this)
        tvClear.setOnClickListener(this)
        tvVersion.setOnClickListener(this)
        tvAuthor.setOnClickListener(this)
        tvProject.setOnClickListener(this)
        tvCopyright.setOnClickListener(this)
        tvLogout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            //清除缓存
            R.id.ivBack->finish()
            //清除缓存
            R.id.tvClear->{}
            //版本
            R.id.tvVersion->{}
            //关于作者
            R.id.tvAuthor->{}
            //项目
            R.id.tvProject->{}
            //版权
            R.id.tvCopyright->{}
            //退出登录
            R.id.tvLogout->{
                presenter?.logout()
                finish()
                ToastUtils.show("已退出登录")
                AppUtils.resetUser()
                intent(LoginActivity::class.java,false)
            }
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

    /**
     * 设置状态栏背景颜色
     */
    override fun setStatusColor() {
        StatusUtils.setUseStatusBarColor(this, com.zs.wanandroid.utils.ColorUtils.parseColor("#ffffff"))
    }



}
