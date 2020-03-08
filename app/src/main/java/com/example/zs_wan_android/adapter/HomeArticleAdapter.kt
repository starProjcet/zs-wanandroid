package com.example.zs_wan_android.adapter

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.zs_wan_android.R
import com.example.zs_wan_android.entity.HomeEntity

class HomeArticleAdapter(layoutResId:Int) :BaseQuickAdapter<HomeEntity.DatasBean,BaseViewHolder>(layoutResId)
,BaseQuickAdapter.OnItemClickListener{

    init {
        onItemClickListener = this
    }

    override fun convert(helper: BaseViewHolder, item: HomeEntity.DatasBean?) {
        val tvTag = helper.getView<TextView>(R.id.tvTag)
        val tvAuthor = helper.getView<TextView>(R.id.tvAuthor)
        val tvDate = helper.getView<TextView>(R.id.tvDate)
        val tvTitle = helper.getView<TextView>(R.id.tvTitle)
        val tvChapterName = helper.getView<TextView>(R.id.tvChapterName)
        if (item?.type==1){
            tvTag.visibility = View.VISIBLE
            tvTag.text = "置顶"
        }else{
            tvTag.visibility = View.GONE
        }
        tvAuthor.text = item?.author
        tvDate.text = item?.niceDate
        tvTitle.text = item?.title
        tvChapterName.text = item?.superChapterName
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

    }

}