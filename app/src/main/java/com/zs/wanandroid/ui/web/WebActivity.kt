package com.zs.wanandroid.ui.web

import android.os.Bundle
import android.webkit.WebView
import com.example.zs_wan_android.R
import com.zs.wanandroid.base.BaseActivity
import com.zs.wanandroid.base.IBasePresenter
import com.zs.wanandroid.constants.Constants
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity<IBasePresenter<*>>() {

    private var loadUrl: String? = null
    private var title: String? = null
    private var link: String? = null
    private var id: Int? = -1
    private var author: String? = null

    override fun init(savedInstanceState: Bundle?) {
        val bundle: Bundle? = intent.extras
        loadUrl = bundle?.getString(Constants.WEB_URL)
        title = bundle?.getString(Constants.WEB_TITLE)
        webView?.loadUrl(loadUrl)
        ivBack.setOnClickListener {
            finish()
        }
        tvTitle.text = title
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_web
    }


}
