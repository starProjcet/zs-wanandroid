package com.example.zs_wan_android.base

import android.os.Bundle
import com.example.zs_wan_android.utils.ColorUtils
import com.example.zs_wan_android.utils.StatusUtil
import com.trello.rxlifecycle3.android.ActivityEvent
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

abstract class BaseActivity<P:IBasePresenter<*>> : RxAppCompatActivity() {

    protected val TAG = javaClass.name
    protected var presenter:P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = getLayoutId()
        if (layoutId != 0) {
            setContentView(layoutId)
        }
        val presenter = createPresenter()
        if (presenter != null) {
            lifecycle.addObserver(presenter)
            presenter.bindLifecycle(this)
        }
        setStatusColor()
        setSystemInvadeBlack()
        init(savedInstanceState)
    }

    /**
     * 设置状态栏背景颜色
     */
    open fun setStatusColor() {
        StatusUtil.setUseStatusBarColor(this, ColorUtils.parseColor("#F60B0B"))
    }

    /**
     * 沉浸式状态
     */
    open fun setSystemInvadeBlack() {
        //第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtil.setSystemStatus(this, true, false)
    }

    protected abstract fun createPresenter(): P?
    protected abstract fun getLayoutId(): Int
    protected abstract fun init(savedInstanceState: Bundle?)
}
