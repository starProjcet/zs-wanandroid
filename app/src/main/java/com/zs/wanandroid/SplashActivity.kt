package com.zs.wanandroid

import android.os.Bundle
import android.view.animation.*
import com.zs.wanandroid.base.BaseActivity
import com.zs.wanandroid.base.IBasePresenter
import com.zs.wanandroid.main.MainActivity
import com.example.zs_wan_android.R
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.TimeUnit

/**
 * 开屏页
 *
 * @author zs
 * @data 2020-03-07
 */
class SplashActivity : BaseActivity<IBasePresenter<*>>() {


    private var disposable:Disposable? = null

    override fun init(savedInstanceState: Bundle?) {
        anim()
        disposable = Observable.timer(2500,TimeUnit.MILLISECONDS)
            .subscribe {
                intent(MainActivity::class.java,true)
                finish()
            }
    }

    /**
     * logo动画
     */
    private fun anim(){
        ivLogo.animation = AnimationUtils.loadAnimation(this,
            R.anim.logo_anim
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }
}
