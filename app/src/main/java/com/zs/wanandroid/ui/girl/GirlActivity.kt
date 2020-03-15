package com.zs.wanandroid.ui.girl

import android.os.Bundle
import com.example.zs_wan_android.R
import com.zs.wanandroid.base.BaseActivity
import com.zs.wanandroid.base.IBasePresenter
import com.zs.wanandroid.base.IBaseView
import com.zs.wanandroid.constants.Constants
import com.zs.wanandroid.proxy.ImageLoad
import com.zs.wanandroid.utils.StatusUtils
import kotlinx.android.synthetic.main.activity_girl.*

/**
 * 美丽的小姐姐界面
 *
 * @author zs
 * @data 2020-03-15
 */
class GirlActivity : BaseActivity<IBasePresenter<IBaseView>>() {


    override fun init(savedInstanceState: Bundle?) {
        ImageLoad.load(ivGirl,Constants.GIRL3)
    }

    override fun createPresenter(): IBasePresenter<IBaseView>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_girl
    }


    /**
     * 沉浸式状态
     */
    override fun setSystemInvadeBlack() {
        //第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtils.setSystemStatus(this, true, false)
    }

}
