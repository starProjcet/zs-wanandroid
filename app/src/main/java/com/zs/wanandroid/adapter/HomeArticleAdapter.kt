package com.zs.wanandroid.adapter

import android.text.TextUtils
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.zs_wan_android.R
import com.zs.wanandroid.entity.HomeEntity
import com.zs.wanandroid.utils.ColorUtils

class HomeArticleAdapter(layoutResId:Int) :BaseQuickAdapter<HomeEntity.DatasBean,BaseViewHolder>(layoutResId)
    ,BaseQuickAdapter.OnItemClickListener{

    init {
        onItemClickListener = this
    }

    override fun convert(helper: BaseViewHolder, item: HomeEntity.DatasBean?) {
        item?.run {
            if (type==1){
                helper.setText(R.id.tvTag,"置顶 ")
                helper.setVisible(R.id.tvTag,true)
                helper.setTextColor(R.id.tvTag, ColorUtils.parseColor(R.color.theme))
            }else{
                helper.setGone(R.id.tvTag,true)
            }
            helper.setText(R.id.tvAuthor,if (!TextUtils.isEmpty(author))author else shareUser)
            helper.setText(R.id.tvDate,niceDate)
            helper.setText(R.id.tvTitle,title)
            helper.setText(R.id.tvChapterName,superChapterName)
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

    }

}