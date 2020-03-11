package com.zs.wanandroid.main.mine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.zs_wan_android.R
import com.zs.wanandroid.base.BaseFragment
import com.zs.wanandroid.base.IBasePresenter
import com.zs.wanandroid.main.set.SetActivity
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * A simple [Fragment] subclass.
 */
class MineFragment : BaseFragment<IBasePresenter<*>>(),View.OnClickListener{


    override fun init(savedInstanceState: Bundle?) {
        llHistory.setOnClickListener(this)
        llRanking.setOnClickListener(this)
        rlIntegral.setOnClickListener(this)
        rlCollect.setOnClickListener(this)
        rlIntegral.setOnClickListener(this)
        rlArticle.setOnClickListener(this)
        rlWebsite.setOnClickListener(this)
        rlGirl.setOnClickListener(this)
        rlSet.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.llHistory->{}
            R.id.llRanking->{}
            R.id.rlIntegral->{}
            R.id.rlCollect->{}
            R.id.rlArticle->{}
            R.id.rlWebsite->{}
            R.id.rlGirl->{}
            R.id.rlSet->{
                intent(SetActivity::class.java,false)
            }
        }
    }


    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }




}
