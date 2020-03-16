package com.zs.wanandroid.ui.main.system.list

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseViewHolder
import com.example.zs_wan_android.R
import com.zs.wanandroid.adapter.OnSystemClickListener
import com.zs.wanandroid.adapter.SystemAdapter
import com.zs.wanandroid.base.LazyFragment
import com.zs.wanandroid.entity.SystemListEntity
import com.zs.wanandroid.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_system_list.*


/**
 * des 体系
 * @author zs
 * @data 2020-03-16
 */
class SystemListFragment : LazyFragment<SystemListContract.Presenter<SystemListContract.View>>()
    ,SystemListContract.View ,OnSystemClickListener{

    private var systemAdapter:SystemAdapter? = null

    override fun lazyInit() {
        rvSystem.layoutManager = LinearLayoutManager(context)
        systemAdapter = SystemAdapter(R.layout.item_system)
        systemAdapter?.setOnSystemClickListener(this)
        rvSystem.adapter = systemAdapter
        loadingTip.loading()
        presenter?.loadData()
    }

    override fun showList(list: MutableList<SystemListEntity>) {
        loadingTip.dismiss()
        systemAdapter?.setNewData(list)
    }

    override fun onError(error: String) {
        loadingTip.dismiss()
        ToastUtils.show(error)
    }

    override fun onCollectClick(helper: BaseViewHolder, i: Int, j: Int) {
        ToastUtils.show("i:$i--j:$j")
    }


    override fun createPresenter():SystemListContract.Presenter<SystemListContract.View> {
        return SystemListPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system_list
    }

}
