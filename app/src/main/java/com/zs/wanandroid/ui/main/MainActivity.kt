package com.zs.wanandroid.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import com.example.zs_wan_android.R
import com.zs.wanandroid.base.BaseActivity
import com.zs.wanandroid.base.IBasePresenter
import com.zs.wanandroid.base.IBaseView
import com.zs.wanandroid.ui.main.account.OfficialAccountFragment
import com.zs.wanandroid.ui.main.home.HomeFragment
import com.zs.wanandroid.ui.main.mine.MineFragment
import com.zs.wanandroid.ui.main.project.ProjectFragment
import com.zs.wanandroid.ui.main.square.SquareFragment
import kotlinx.android.synthetic.main.activity_main.*


/**
 * activity基础类
 *
 * @author zs
 * @data 2020-03-07
 */
class MainActivity : BaseActivity<IBasePresenter<*>>(),
    IBaseView {

    private var lastIndex = 0
    private var fragments: MutableList<Fragment> = mutableListOf()

    override fun init(savedInstanceState: Bundle?) {
        initFragment()
        initBottom()
    }

    private fun initFragment() {
        fragments.add(HomeFragment())
        fragments.add(ProjectFragment())
        fragments.add(SquareFragment())
        fragments.add(OfficialAccountFragment())
        fragments.add(MineFragment())
        setFragmentPosition(0)
    }

    private fun initBottom() {
        btmNavigation.run {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> setFragmentPosition(0)
                    R.id.menu_project -> setFragmentPosition(1)
                    R.id.menu_square -> setFragmentPosition(2)
                    R.id.menu_official_account -> setFragmentPosition(3)
                    R.id.menu_mine -> setFragmentPosition(4)
                }
                // 这里注意返回true,否则点击失效
                true
            }
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
            ft.setMaxLifecycle(currentFragment, Lifecycle.State.RESUMED)
        }
        ft.show(currentFragment)
        ft.commit()
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

    var lastTime: Long = 0
    override fun onBackPressed() {
        if (System.currentTimeMillis() - this.lastTime > 2000L) {
            com.zs.wanandroid.utils.ToastUtils.show("再按一次退出程序")
            this.lastTime = System.currentTimeMillis()
            return
        } else {
            super.onBackPressed()
        }
    }
}
