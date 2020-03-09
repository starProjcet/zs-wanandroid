package com.example.zs_wan_android.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.zs_wan_android.http.LogoutEvent
import com.example.zs_wan_android.utils.ColorUtils
import com.example.zs_wan_android.utils.StatusUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseActivity<P:IBasePresenter<*>> : AppCompatActivity() {

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
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    /**
     * 设置状态栏背景颜色
     */
    open fun setStatusColor() {
        StatusUtils.setUseStatusBarColor(this, ColorUtils.parseColor("#F60B0B"))
    }

    /**
     * 沉浸式状态
     */
    open fun setSystemInvadeBlack() {
        //第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtils.setSystemStatus(this, true, false)
    }

    protected abstract fun createPresenter(): P?
    protected abstract fun getLayoutId(): Int
    protected abstract fun init(savedInstanceState: Bundle?)

    /**
     * 退出登陆消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun logoutEvent(logoutEvent: LogoutEvent){
        finish()
    }
}
