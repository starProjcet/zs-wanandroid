package com.zs.wanandroid.ui.main.mine

import android.os.Bundle
import android.view.View
import com.example.zs_wan_android.R
import com.zs.wanandroid.base.BaseFragment
import com.zs.wanandroid.entity.IntegralEntity
import com.zs.wanandroid.http.LoginEvent
import com.zs.wanandroid.http.LogoutEvent
import com.zs.wanandroid.ui.collect.CollectActivity
import com.zs.wanandroid.ui.login.LoginActivity
import com.zs.wanandroid.ui.set.SetActivity
import com.zs.wanandroid.utils.AppManager
import com.zs.wanandroid.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_mine.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * des 我的界面
 * @author zs
 * @data 2020-03-13
 */
class MineFragment : BaseFragment<MineContract.Presenter<MineContract.View>>(),View.OnClickListener,
    MineContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun init(savedInstanceState: Bundle?) {
        setListener()
        if (AppManager.isLogin()) {
            presenter?.loadIntegral()
        }
    }

    private fun setListener(){
        ivHead.setOnClickListener(this)
        tvUserName.setOnClickListener(this)
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
            R.id.ivHead->ToastUtils.show("我只是一只可爱的小老鼠..")
            R.id.tvUserName->{
                if (!AppManager.isLogin()){
                    intent(LoginActivity::class.java,false)
                }
            }
            R.id.llHistory->{}
            R.id.llRanking->{}
            R.id.rlIntegral->{}
            R.id.rlCollect-> intent(CollectActivity::class.java,true)
            R.id.rlArticle->{}
            R.id.rlWebsite->{}
            R.id.rlGirl->{}
            R.id.rlSet-> intent(SetActivity::class.java,false)

        }
    }

    override fun showIntegral(integralEntity: IntegralEntity) {
        tvUserName.text = integralEntity.username
        tvId.text = String.format("%s","id:${integralEntity.userId}")
        tvRanking.text = integralEntity.rank.toString()
        tvIntegral.text = integralEntity.coinCount.toString()
    }
    override fun onError(error: String) {
        ToastUtils.show(error)
    }

    override fun createPresenter(): MineContract.Presenter<MineContract.View>? {
        return MinePresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    /**
     * 登陆消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun loginEvent(loginEvent: LoginEvent){
        presenter?.loadIntegral()
    }

    /**
     * 退出消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun logoutEvent(loginEvent: LogoutEvent){
        tvUserName.text = "请先登录"
        tvId.text = "--"
        tvRanking.text = "0"
        tvIntegral.text = "0"
    }

}
