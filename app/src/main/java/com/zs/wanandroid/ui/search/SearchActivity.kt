package com.zs.wanandroid.ui.search

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.widget.RelativeLayout
import com.example.zs_wan_android.R
import com.zs.wanandroid.base.BaseActivity
import com.zs.wanandroid.base.IBasePresenter
import com.zs.wanandroid.base.IBaseView
import com.zs.wanandroid.utils.UIUtils
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity<IBasePresenter<IBaseView>>() {

    private var rlInitWidth: Int = 0
    override fun init(savedInstanceState: Bundle?) {
        startSearchAnim()
        ivBack.setOnClickListener {
            finish()
        }
    }

    override fun createPresenter(): IBasePresenter<IBaseView>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    /**
     * 搜索过渡动画
     */
    private fun startSearchAnim() {
        rlTop.post {
            rlInitWidth = rlTop.measuredWidth
            var left = rlTop.left - UIUtils.dip2px(this, 10f)
            val animator = ValueAnimator.ofInt(rlInitWidth, left)
            animator.duration = 300
            animator.interpolator = DecelerateInterpolator()
            animator.addUpdateListener { animation ->
                //获取改变后的值
                val currentValue = animation.animatedValue as Int
                //输出改变后的值
                val params = rlTop.getLayoutParams() as RelativeLayout.LayoutParams
                params.width = currentValue
                rlTop.layoutParams = params
                if (currentValue == left) {
                    //startLabelAnim()
                    editText.isFocusable = true
                    editText.isFocusableInTouchMode = true
                    editText.requestFocus()
                    //KeyBoardUtil.openKeybord(editText, UIUtils.getContext())
                }
            }
            //启动动画
            animator.start()
        }
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, R.anim.search_out_anim)
    }


    /**
     * 重写finish方法，实现退出动画
     */
    override fun finish() {
        super.finish()
        val rlWidth = rlTop.measuredWidth
        val animator = ValueAnimator.ofInt(rlWidth, rlInitWidth)
        animator.duration = 500
        animator.interpolator = DecelerateInterpolator()
        animator.addUpdateListener {
            //获取改变后的值
            val currentValue = animator.animatedValue as Int
            //输出改变后的值
            val params = rlTop.layoutParams as RelativeLayout.LayoutParams
            params.width = currentValue
            rlTop.layoutParams = params
        }
        animator.start()
    }
}
