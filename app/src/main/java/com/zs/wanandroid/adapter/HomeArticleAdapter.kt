package com.zs.wanandroid.adapter

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.zs_wan_android.R
import com.zs.wanandroid.entity.HomeEntity
import com.zs.wanandroid.utils.ColorUtils

class HomeArticleAdapter(layoutResId:Int) :BaseQuickAdapter<HomeEntity.DatasBean,BaseViewHolder>(layoutResId) {

    private var collectClickListener:OnCollectClickListener? = null

    fun setCollectClickListener(collectClickListener:OnCollectClickListener){
        this.collectClickListener = collectClickListener
    }

    override fun convert(helper: BaseViewHolder, item: HomeEntity.DatasBean?) {
        item?.run {
            if (type==1){
                helper.setText(R.id.tvTag,"置顶 ")
                helper.setTextColor(R.id.tvTag, ColorUtils.parseColor(R.color.red))
            }else{
                helper.setText(R.id.tvTag,"")
            }
            helper.setText(R.id.tvAuthor,if (!TextUtils.isEmpty(author))author else shareUser)
            helper.setText(R.id.tvDate,niceDate)
            helper.setText(R.id.tvTitle,title)
            helper.setText(R.id.tvChapterName,superChapterName)
            helper.getView<ImageView>(R.id.ivCollect)
                .apply {
                    setOnClickListener {
                        collectClickListener?.onCollectClick(helper,helper.adapterPosition)
                    }
                    if (item.collect) {
                        setImageResource(R.mipmap.article_collect)
                    }else{
                        setImageResource(R.mipmap.article_un_collect)
                    }
                }
        }
    }

}