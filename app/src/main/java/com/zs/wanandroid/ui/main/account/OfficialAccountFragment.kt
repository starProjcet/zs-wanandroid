package com.zs.wanandroid.ui.main.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.zs_wan_android.R

/**
 * des 公众号
 * @author zs
 * @data 2020-03-16
 */
class OfficialAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_official_account, container, false)
    }

}
