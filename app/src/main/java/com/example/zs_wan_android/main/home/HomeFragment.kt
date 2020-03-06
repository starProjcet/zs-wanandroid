package com.example.zs_wan_android.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.zs_wan_android.R
import com.example.zs_wan_android.base.BaseFragment
import com.example.zs_wan_android.base.IBasePresenter

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment<IBasePresenter<*>>() {


    override fun init(savedInstanceState: Bundle?) {

    }


    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }




}
