package com.zs.wanandroid.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.zs_wan_android.R
import com.zs.wanandroid.adapter.HomeArticleAdapter
import com.zs.wanandroid.base.BaseFragment
import com.zs.wanandroid.entity.BannerEntity
import com.zs.wanandroid.entity.HomeEntity
import com.zs.wanandroid.utils.ToastUtils
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.zs.wanandroid.constants.Constants
import com.zs.wanandroid.ui.search.SearchActivity
import com.zs.wanandroid.ui.web.WebActivity
import com.zs.wanandroid.weight.ReloadListener
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * 首页
 *
 * @author zs
 * @data 2020-03-09
 */
class HomeFragment : BaseFragment<HomeContract.Presenter<HomeContract.View>>() ,BGABanner.Adapter<ImageView?, String?>
,BGABanner.Delegate<ImageView?, String?> , HomeContract.View,OnLoadMoreListener,OnRefreshListener,ReloadListener
,BaseQuickAdapter.OnItemClickListener{


    private var pageNum:Int = 0
    private var article = mutableListOf<HomeEntity.DatasBean>()
    private var bannerList = mutableListOf<BannerEntity>()
    private var homeArticleAdapter: HomeArticleAdapter? = null
    override fun init(savedInstanceState: Bundle?) {
        initView()
        loadingTip.loading()
        loadData()
    }

    private fun initView(){
        homeArticleAdapter =
            HomeArticleAdapter(R.layout.item_home_article)
        homeArticleAdapter?.onItemClickListener = this
        homeArticleAdapter?.setNewData(article)
        rvHomeList.adapter = homeArticleAdapter
        loadingTip.setReloadListener(this)
        smartRefresh?.setOnRefreshListener(this)
        smartRefresh?.setOnLoadMoreListener(this)
        addScrollListener()
        rvHomeList.layoutManager = LinearLayoutManager(context)
        ivSearch.setOnClickListener{
            intent(SearchActivity::class.java,false)
            //瞬间开启activity，无动画
            activity?.overridePendingTransition(0, 0)

        }
    }

    /**
     * 加载数据
     * 初始化，网络出错重新加载，刷新均可使用
     */
    private fun loadData(){
        //banner只加载一次
        if (bannerList.size==0){
            presenter?.loadBanner()
        }
        article.clear()
        pageNum = 0
        presenter?.loadData(pageNum)
    }

    /**
     * 为NestedScrollView增加滑动事件
     * 改变搜索框的透明度
     */
    private fun addScrollListener(){
        nestedView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener
        { _, _, scrollY, _, _ ->
            val alpha = if (scrollY>0){
                ivSearch.isEnabled = true
                scrollY.toFloat() / (300).toFloat()
            }else{
                ivSearch.isEnabled = false
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
        intent(Bundle().apply {
            putString(Constants.WEB_URL,bannerList[position].url)
            putString(Constants.WEB_TITLE,bannerList[position].title)
        },WebActivity::class.java,false)
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

    /**
     * 文章列表加载成功
     */
    override fun showList(list: MutableList<HomeEntity.DatasBean>) {
        dismissRefresh()
        loadingTip.dismiss()
        if (list.isNotEmpty()){
            article.addAll(list)
            homeArticleAdapter?.setNewData(article)
        }else {
            ToastUtils.show("没有数据啦...")
        }
    }

    override fun showBanner(bannerList:MutableList<BannerEntity>) {
        this.bannerList.addAll(bannerList)
        initBanner()
    }

    override fun onError(error: String) {
        //请求失败将page -1
        if (pageNum>0)pageNum--
        dismissRefresh()
        ToastUtils.show(error)
    }

    /**
     * 加载更多
     */
    override fun onLoadMore(refreshLayout: RefreshLayout) {
        presenter?.loadData(pageNum++)
    }

    /**
     * 刷新
     */
    override fun onRefresh(refreshLayout: RefreshLayout) {
        loadData()
    }

    /**
     * 无网络，重新加载
     */
    override fun reload() {
        loadingTip.loading()
        loadData()
    }


    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        intent(Bundle().apply {
            putString(Constants.WEB_URL,article[position].link)
            putString(Constants.WEB_TITLE,article[position].title)
        },WebActivity::class.java,false)
    }

    /**
     * 隐藏刷新加载
     */
    private fun dismissRefresh() {
        if (smartRefresh.state.isOpening) {
            smartRefresh.finishLoadMore()
            smartRefresh.finishRefresh()
        }
    }

    override fun createPresenter(): HomeContract.Presenter<HomeContract.View>? {
        return HomePresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }
}
