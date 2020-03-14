package com.zs.wanandroid.ui.main.project

import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.zs_wan_android.R
import com.zs.wanandroid.adapter.FragmentListAdapter
import com.zs.wanandroid.adapter.TabAdapter
import com.zs.wanandroid.base.BaseFragment
import com.zs.wanandroid.entity.ProjectTabEntity
import com.zs.wanandroid.ui.main.project.list.ProjectListFragment
import com.zs.wanandroid.utils.ToastUtils
import com.zs.wanandroid.weight.indicator.OnTabClickListener
import kotlinx.android.synthetic.main.fragment_project.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

/**
 * 项目界面
 *
 * @author zs
 * @data 2020-03-14
 */
class ProjectFragment : BaseFragment<ProjectContract.Presenter<ProjectContract.View>>()
    ,ProjectContract.View ,OnTabClickListener{

    private var tabList = mutableListOf<ProjectTabEntity>()
    override fun init(savedInstanceState: Bundle?) {
        if (VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            flTop.elevation = 10f
        }
        presenter?.loadData()
    }

    override fun showList(list: MutableList<ProjectTabEntity>) {
        tabList.clear()
        tabList.addAll(list)
        initFragment()
    }

    private fun initFragment(){
        val fragmentList = mutableListOf<Fragment>()
        tabList.forEach {
            val fragment = ProjectListFragment()
            val bundle = Bundle()
            bundle.putInt("id", it.id)
            bundle.putString("name", it.name)
            fragment.arguments = bundle
            fragmentList.add(fragment)
        }
        val adapter = FragmentListAdapter(fragmentList,childFragmentManager)
        viewPager.offscreenPageLimit = 6
        viewPager.adapter = adapter
        val commonNavigator = CommonNavigator(context)
        val tabAdapter = TabAdapter(tabList)
        //tab点击事件
        tabAdapter.setOnTabClickListener(this)
        commonNavigator.adapter = tabAdapter
        magicView.navigator = commonNavigator
        //将magicView和viewPager进行绑定
        ViewPagerHelper.bind(magicView,viewPager)
    }

    override fun onError(error: String) {
        ToastUtils.show(error)
    }

    override fun createPresenter(): ProjectContract.Presenter<ProjectContract.View>? {
        return ProjectPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun onTabClick(view: View,index:Int) {
        viewPager.currentItem = index
    }
}
