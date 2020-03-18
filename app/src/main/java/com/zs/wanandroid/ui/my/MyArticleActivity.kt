package com.zs.wanandroid.ui.my

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.zs_wan_android.R
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.zs.wanandroid.adapter.MyArticleAdapter
import com.zs.wanandroid.adapter.OnDeleteClickListener
import com.zs.wanandroid.base.BaseActivity
import com.zs.wanandroid.constants.Constants
import com.zs.wanandroid.entity.ArticleEntity
import com.zs.wanandroid.entity.MyArticleEntity
import com.zs.wanandroid.ui.add.AddArticleActivity
import com.zs.wanandroid.ui.web.WebActivity
import com.zs.wanandroid.utils.ToastUtils
import com.zs.wanandroid.weight.ReloadListener
import kotlinx.android.synthetic.main.activity_my_article.*
import kotlinx.android.synthetic.main.activity_my_article.ivBack
import kotlinx.android.synthetic.main.activity_my_article.loadingTip
import kotlinx.android.synthetic.main.activity_my_article.smartRefresh

/**
 * 我的文章
 * @author zs
 * @data 2020-03-17
 */
class MyArticleActivity : BaseActivity<MyArticleContract.Presenter<MyArticleContract.View>>()
    ,MyArticleContract.View , OnLoadMoreListener, OnRefreshListener, ReloadListener
    ,BaseQuickAdapter.OnItemClickListener,OnDeleteClickListener {

    private var currentPosition = 0
    private var articleAdapter: MyArticleAdapter? = null
    private var pageNum = 1
    private var articleList = mutableListOf<ArticleEntity.DatasBean>()

    override fun init(savedInstanceState: Bundle?) {
        initView()
        loadingTip.loading()
        loadData()
    }

    private fun initView(){
        ivBack.setOnClickListener {
            finish()
        }
        ivAdd.setOnClickListener {
            intent(AddArticleActivity::class.java,true)
        }
        loadingTip.setReloadListener(this)
        smartRefresh?.setOnRefreshListener(this)
        smartRefresh?.setOnLoadMoreListener(this)
        rvMyArticle.layoutManager = LinearLayoutManager(this)
        articleAdapter = MyArticleAdapter(R.layout.item_my_article)
        articleAdapter?.setonDeleteClickListener(this)
        articleAdapter?.onItemClickListener = this
        rvMyArticle.adapter = articleAdapter
    }

    /**
     * 加载数据
     * 初始化，网络出错重新加载，刷新均可使用
     */
    private fun loadData(){
        articleList.clear()
        articleAdapter?.setNewData(articleList)
        pageNum = 1
        presenter?.loadData(pageNum)
    }

    override fun showList(t: MyArticleEntity) {
        dismissRefresh()
        loadingTip.dismiss()
        if (t.shareArticles?.datas?.isNotEmpty()!!){
            articleList.addAll(t.shareArticles?.datas!!)
            articleAdapter?.setNewData(articleList)
        }else {
            if (articleList.size==0)loadingTip.showEmpty()
            else ToastUtils.show("没有数据啦...")
        }
    }

    override fun deleteSuccess() {
        articleAdapter?.delete(currentPosition)
    }

    override fun onError(error: String) {
        //请求失败将page -1
        if (pageNum>0)pageNum--
        dismissRefresh()
        ToastUtils.show(error)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        pageNum++
        presenter?.loadData(pageNum)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        loadData()
    }

    override fun reload() {
        loadingTip.loading()
        loadData()
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        intent(Bundle().apply {
            putString(Constants.WEB_URL,articleList[position].link)
            putString(Constants.WEB_TITLE,articleList[position].title)
        }, WebActivity::class.java,false)
    }

    override fun onDeleteClick(helper: BaseViewHolder, position: Int) {
        if (position<articleList.size){
            //记录当前点击的item
            currentPosition = position
            presenter?.delete(articleList[position].id)
        }
    }

    override fun createPresenter(): MyArticleContract.Presenter<MyArticleContract.View>? {
        return MyArticlePresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_my_article
    }

    override fun getContext(): Context? {
        return this
    }

    /**
     * 隐藏刷新加载
     */
    private fun dismissRefresh() {
        loadingTip.dismiss()
        if (smartRefresh.state.isOpening) {
            smartRefresh.finishLoadMore()
            smartRefresh.finishRefresh()
        }
    }
}
