package com.zs.wanandroid.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zs.wanandroid.main.login.LoginActivity
import com.zs.wanandroid.utils.AppUtils
import com.zs.wanandroid.utils.StatusUtils
import org.greenrobot.eventbus.EventBus

abstract class BaseActivity<P: IBasePresenter<*>> : AppCompatActivity() {

    protected val TAG = javaClass.name
    protected var presenter:P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = getLayoutId()
        if (layoutId != 0) {
            setContentView(layoutId)
        }
        presenter = createPresenter()
        presenter?.let {
            lifecycle.addObserver(it)
        }

        setStatusColor()
        setSystemInvadeBlack()
        init(savedInstanceState)
        //EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        //EventBus.getDefault().unregister(this)
    }

    /**
     * 设置状态栏背景颜色
     */
    protected open fun setStatusColor() {
        StatusUtils.setUseStatusBarColor(this, com.zs.wanandroid.utils.ColorUtils.parseColor("#00ffffff"))
    }

    /**
     * 沉浸式状态
     */
    protected open  fun setSystemInvadeBlack() {
        //第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtils.setSystemStatus(this, true, true)
    }

//    /**
//     * 退出登陆消息
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public fun logoutEvent(logoutEvent: LogoutEvent){
//        finish()
//    }

    /**
     * 界面跳转
     * @param isLogin 启动界面是否需要登录
     */
    protected fun intent(clazz:Class<*>,isLogin:Boolean){
        //需要登录&&未登录
        if (isLogin && !AppUtils.isLogin()) {
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            startActivity(Intent(this,clazz))
        }
    }
    /**
     * 携带bundle跳转
     * @param isLogin 启动界面是否需要登录
     */
    protected fun intent(bundle: Bundle,clazz:Class<*>,isLogin:Boolean){
        //需要登录&&未登录
        if (isLogin && !AppUtils.isLogin()) {
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            startActivity(Intent(this, clazz).apply {
                putExtra("bundle", bundle)
            })
        }
    }

    protected abstract fun init(savedInstanceState: Bundle?)
    protected abstract fun createPresenter(): P?
    protected abstract fun getLayoutId(): Int

}
