package com.example.zs_wan_android.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import cn.bingoogolapple.bgabanner.BGABanner
import com.example.zs_wan_android.R
import com.example.zs_wan_android.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment<HomeContract.Presenter<HomeContract.View>>() ,BGABanner.Adapter<ImageView?, String?>
,BGABanner.Delegate<ImageView?, String?> ,HomeContract.View{


    override fun init(savedInstanceState: Bundle?) {
        initBanner()
        addScrollListener()
        presenter?.loadData()
    }

    /**
     * 初始化banner
     */
    private fun initBanner(){
        val views: MutableList<View> = ArrayList()
        views.add(LayoutInflater.from(context).inflate(R.layout.banner_layout,null).findViewById(R.id.ivBanner))
        views.add(LayoutInflater.from(context).inflate(R.layout.banner_layout,null).findViewById(R.id.ivBanner))
        views.add(LayoutInflater.from(context).inflate(R.layout.banner_layout,null).findViewById(R.id.ivBanner))
        banner.setData(views)
        banner.setAdapter(this)
        banner.setDelegate(this)
    }

    /**
     * 为NestedScrollView增加滑动事件
     * 改变搜索框的透明度
     */
    private fun addScrollListener(){
        nestedView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener
        { _, _, scrollY, _, _ ->
            var alpha = if (scrollY>0){
                scrollY.toFloat() / (500).toFloat()
            }else{
                0f
            }
            rlSearch.alpha = alpha
        })
    }

    override fun createPresenter(): HomeContract.Presenter<HomeContract.View>? {
        return HomePresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    /**
     * 填充banner
     */
    override fun fillBannerItem(banner: BGABanner?, itemView: ImageView?, model: String?, position: Int) {
        itemView?.setImageResource(R.mipmap.ic_launcher_round)
        itemView?.scaleType = ImageView.ScaleType.CENTER_CROP
    }

    /**
     * banner点击事件
     */
    override fun onBannerItemClick(banner: BGABanner?, itemView: ImageView?, model: String?, position: Int) {

    }

    override fun showData() {
    }

    override fun onError(error: String) {
    }


}
