package com.zs.wanandroid.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.example.zs_wan_android.R
import com.zs.wanandroid.adapter.HomeArticleAdapter
import com.zs.wanandroid.base.BaseFragment
import com.zs.wanandroid.entity.BannerEntity
import com.zs.wanandroid.entity.HomeEntity
import com.zs.wanandroid.utils.ToastUtils
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * 首页
 *
 * @author zs
 * @data 2020-03-09
 */
class HomeFragment : BaseFragment<HomeContract.Presenter<HomeContract.View>>() ,BGABanner.Adapter<ImageView?, String?>
,BGABanner.Delegate<ImageView?, String?> , HomeContract.View,OnLoadMoreListener,OnRefreshListener{

    private var pageNum:Int = 0
    private var article = mutableListOf<HomeEntity.DatasBean>()
    private var bannerList = mutableListOf<BannerEntity>()
    private var homeArticleAdapter: HomeArticleAdapter? = null
    override fun init(savedInstanceState: Bundle?) {
        initView()
        presenter?.loadBanner()
        presenter?.loadData(pageNum)
    }

    private fun initView(){
        smartRefresh?.setOnRefreshListener(this)
        smartRefresh?.setOnLoadMoreListener(this)
        addScrollListener()
        rvHomeList.layoutManager = LinearLayoutManager(context)
        tvSearch.setOnClickListener{

        }

    }

    /**
     * 为NestedScrollView增加滑动事件
     * 改变搜索框的透明度
     */
    private fun addScrollListener(){
        nestedView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener
        { _, _, scrollY, _, _ ->
            val alpha = if (scrollY>0){
                scrollY.toFloat() / (300).toFloat()
            }else{
                0f
            }
            rlSearch.alpha = alpha
        })
    }

    /**
     * 填充banner
     */
    override fun fillBannerItem(banner: BGABanner?, itemView: ImageView?, model: String?, position: Int) {
        itemView?.let {
            itemView.scaleType = ImageView.ScaleType.CENTER_CROP
            val bannerEntity = bannerList[position]
            Glide.with(this)
                .load(bannerEntity.imagePath)
                .into(itemView)
        }
    }

    /**
     * banner点击事件
     */
    override fun onBannerItemClick(banner: BGABanner?, itemView: ImageView?, model: String?, position: Int) {
    }

    /**
     * 初始化banner
     */
    private fun initBanner(){
        banner.setAutoPlayAble(true)
        val views: MutableList<View> = ArrayList()
        bannerList.forEach { _ ->
            views.add(LayoutInflater.from(context).inflate(R.layout.banner_layout,null).findViewById(R.id.ivBanner))
        }
        banner.setAdapter(this)
        banner.setDelegate(this)
        banner.setData(views)
    }

    override fun showList(list: MutableList<HomeEntity.DatasBean>) {
        article.addAll(list)
        if (homeArticleAdapter==null){
            homeArticleAdapter =
                HomeArticleAdapter(R.layout.item_home_article)
            homeArticleAdapter?.setNewData(article)
            rvHomeList.adapter = homeArticleAdapter
        }
    }

    override fun showBanner(bannerList:MutableList<BannerEntity>) {
        this.bannerList.addAll(bannerList)
        initBanner()
    }

    override fun onError(error: String) {
        ToastUtils.show(error)
    }

    /**
     * 加载更多
     */
    override fun onLoadMore(refreshLayout: RefreshLayout) {

    }

    /**
     * 刷新
     */
    override fun onRefresh(refreshLayout: RefreshLayout) {

    }

    override fun createPresenter(): HomeContract.Presenter<HomeContract.View>? {
        return HomePresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }
}
