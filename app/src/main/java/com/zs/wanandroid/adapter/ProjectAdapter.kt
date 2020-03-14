package com.zs.wanandroid.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.zs_wan_android.R
import com.zs.wanandroid.entity.ProjectEntity
import com.zs.wanandroid.proxy.ImageLoad
import com.zs.wanandroid.weight.OnLimitClickHelper
import com.zs.wanandroid.weight.OnLimitClickListener
import kotlinx.android.synthetic.main.item_project.view.*

class ProjectAdapter(layoutId:Int):BaseQuickAdapter<ProjectEntity,BaseViewHolder>(layoutId) {

    private var onCollectClickListener:OnCollectClickListener? = null
    fun setOnCollectClickListener(onCollectClickListener:OnCollectClickListener?){
        this.onCollectClickListener = onCollectClickListener
    }

    override fun convert(helper: BaseViewHolder, item: ProjectEntity?) {
        item?.apply {
            ImageLoad.loadRadius(helper.getView(R.id.ivTitle),envelopePic,20)
            helper.setText(R.id.tvTitle,title)
            helper.setText(R.id.tvDes,desc)
            helper.setText(R.id.tvNameData,"$niceDate | $author")
            helper.getView<ImageView>(R.id.ivCollect).apply {
                setOnClickListener(OnLimitClickHelper(OnLimitClickListener {
                    onCollectClickListener?.onCollectClick(helper, helper.adapterPosition)
                }))
                if (item.collect) {
                    setImageResource(R.mipmap.article_collect)
                }else{
                    setImageResource(R.mipmap.article_un_collect)
                }
            }
        }
    }
}