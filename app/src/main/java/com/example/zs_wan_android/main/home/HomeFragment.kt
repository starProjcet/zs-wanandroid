package com.example.zs_wan_android.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import cn.bingoogolapple.bgabanner.BGABanner
import com.example.zs_wan_android.R
import com.example.zs_wan_android.base.BaseFragment
import com.example.zs_wan_android.base.IBasePresenter
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment<IBasePresenter<*>>() {


    override fun init(savedInstanceState: Bundle?) {
        val views: MutableList<View> = ArrayList()
        views.add(LayoutInflater.from(context).inflate(R.layout.banner_layout,null).findViewById(R.id.ivBanner))
        views.add(LayoutInflater.from(context).inflate(R.layout.banner_layout,null).findViewById(R.id.ivBanner))
        views.add(LayoutInflater.from(context).inflate(R.layout.banner_layout,null).findViewById(R.id.ivBanner))
        banner.setData(views)
        banner.setAdapter(BGABanner.Adapter<ImageView?, String?> { banner, itemView, model, position ->
            itemView?.setImageResource(R.mipmap.ic_launcher_round)
            itemView?.scaleType = ImageView.ScaleType.CENTER_CROP
        })
        banner.setDelegate(BGABanner.Delegate<ImageView?, String?> { banner, itemView, model, position ->
            Log.i("HomeFragment","position:"+position)
        })
    }


    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }




}
