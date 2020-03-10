package com.example.zs_wan_android.adapter

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.zs_wan_android.R
import com.example.zs_wan_android.entity.HomeEntity
import com.example.zs_wan_android.utils.ColorUtils

class HomeArticleAdapter(layoutResId:Int) :BaseQuickAdapter<HomeEntity.DatasBean,BaseViewHolder>(layoutResId)
,BaseQuickAdapter.OnItemClickListener{

    init {
        onItemClickListener = this
    }

    override fun convert(helper: BaseViewHolder, item: HomeEntity.DatasBean?) {
        if (item?.type==1){
            helper.setText(R.id.tvTag,"置顶 ")
            helper.setVisible(R.id.tvTag,true)
            helper.setTextColor(R.id.tvTag,ColorUtils.parseColor(R.color.theme))
        }else{
            helper.setGone(R.id.tvTag,true)
        }
        helper.setText(R.id.tvAuthor,item?.author)
        helper.setText(R.id.tvDate,item?.niceDate)
        helper.setText(R.id.tvTitle,item?.title)
        helper.setText(R.id.tvChapterName,item?.superChapterName)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

    }

}