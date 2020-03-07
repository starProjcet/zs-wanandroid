package com.example.zs_wan_android.main

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.zs_wan_android.R
import com.example.zs_wan_android.base.BaseActivity
import com.example.zs_wan_android.base.IBasePresenter
import com.example.zs_wan_android.base.IBaseView
import com.example.zs_wan_android.main.account.OfficialAccountFragment
import com.example.zs_wan_android.main.home.HomeFragment
import com.example.zs_wan_android.main.mine.MineFragment
import com.example.zs_wan_android.main.project.ProjectFragment
import com.example.zs_wan_android.main.square.SquareFragment
import com.example.zs_wan_android.utils.ColorUtils
import com.example.zs_wan_android.utils.StatusUtil
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


/**
 * activity基础类
 *
 * @author zs
 * @data 2020-03-07
 */
class MainActivity : BaseActivity<IBasePresenter<*>>() ,IBaseView{

    private var lastIndex = 0
    private var fragments: MutableList<Fragment> = mutableListOf()

    override fun init(savedInstanceState: Bundle?) {
       initFragment()
        initBottom()
    }

    private fun initFragment(){
        fragments.add(HomeFragment())
        fragments.add(ProjectFragment())
        fragments.add(SquareFragment())
        fragments.add(OfficialAccountFragment())
        fragments.add(MineFragment())
        setFragmentPosition(0)
    }

    private fun initBottom(){
        btmNavigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED;//默认清除动画（显示文字
        btmNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> setFragmentPosition(0)
                R.id.menu_project -> setFragmentPosition(1)
                R.id.menu_square -> setFragmentPosition(2)
                R.id.menu_official_account -> setFragmentPosition(3)
                R.id.menu_mine -> setFragmentPosition(4)
                else -> {
                }
            }
            // 这里注意返回true,否则点击失效
            true
        }
    }

    private fun setFragmentPosition(position: Int) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        val currentFragment: Fragment = fragments[position]
        val lastFragment: Fragment = fragments[lastIndex]
        lastIndex = position
        ft.hide(lastFragment)
        if (!currentFragment.isAdded) {
            supportFragmentManager.beginTransaction().remove(currentFragment).commit()
            ft.add(R.id.frameLayout, currentFragment)
        }
        ft.show(currentFragment)
        ft.commitAllowingStateLoss()
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getContext(): Context? {
        return this
    }

    override fun onError(error: String) {

    }

    /**
     * 设置状态栏背景颜色
     */
    override fun setStatusColor() {
        StatusUtil.setUseStatusBarColor(this, ColorUtils.parseColor("#00ffffff"))
    }

    /**
     * 沉浸式状态
     */
    override fun setSystemInvadeBlack() {
        //第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtil.setSystemStatus(this, true, true)
    }

}
