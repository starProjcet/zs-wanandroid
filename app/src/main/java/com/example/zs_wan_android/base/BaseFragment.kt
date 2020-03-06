package com.example.zs_wan_android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * fragment基础类
 *
 * @author zs
 * @data 2020-03-07
 */
abstract class BaseFragment<P:IBasePresenter<*>>: Fragment() {

    protected var TAG = javaClass.name
    protected var presenter:P? = null
    var contentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val presenter = createPresenter()
        if (presenter!=null){
            lifecycle.addObserver(presenter)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        contentView = inflater.inflate(getLayoutId(), null)
        init(savedInstanceState)
        return contentView
    }

    protected abstract fun createPresenter(): P?
    protected abstract fun getLayoutId(): Int
    protected abstract fun init(savedInstanceState: Bundle?)

}
