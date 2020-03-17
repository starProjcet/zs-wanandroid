package com.zs.wanandroid.ui.main.account

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.zs_wan_android.R
import com.zs.wanandroid.adapter.FragmentListAdapter
import com.zs.wanandroid.adapter.TabAdapter
import com.zs.wanandroid.base.BaseFragment
import com.zs.wanandroid.entity.TabEntity
import com.zs.wanandroid.ui.main.account.list.OfficialAccountListFragment
import com.zs.wanandroid.ui.main.project.OfficialAccountContract
import com.zs.wanandroid.utils.ToastUtils
import com.zs.wanandroid.weight.indicator.OnTabClickListener
import kotlinx.android.synthetic.main.fragment_project.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

/**
 * des 公众号
 * @author zs
 * @data 2020-03-16
 */
class OfficialAccountFragment : BaseFragment<OfficialAccountContract.Presenter<OfficialAccountContract.View>>()
    , OfficialAccountContract.View , OnTabClickListener {

    private var tabList = mutableListOf<TabEntity>()
    override fun init(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            flTop.elevation = 10f
        }
        presenter?.loadData()
    }

    override fun showList(list: MutableList<TabEntity>) {
        tabList.clear()
        tabList.addAll(list)
        initFragment()
    }

    private fun initFragment(){
        val fragmentList = mutableListOf<Fragment>()
        val list = mutableListOf<String>()
        tabList.forEach {
            val fragment = OfficialAccountListFragment()
            val bundle = Bundle()
            bundle.putInt("id", it.id)
            bundle.putString("name", it.name)
            fragment.arguments = bundle
            fragmentList.add(fragment)
            it.name?.let { it1 -> list.add(it1) }
        }
        val adapter = FragmentListAdapter(fragmentList,childFragmentManager)
        viewPager.offscreenPageLimit = 6
        viewPager.adapter = adapter
        val commonNavigator = CommonNavigator(context)
        val tabAdapter = TabAdapter(list)
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

    override fun createPresenter(): OfficialAccountContract.Presenter<OfficialAccountContract.View>? {
        return OfficialAccountPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun onTabClick(view: View,index:Int) {
        viewPager.currentItem = index
    }
}
