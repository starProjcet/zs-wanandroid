package com.zs.wanandroid.ui.main.mine

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.zs_wan_android.R
import com.google.gson.Gson
import com.zs.wanandroid.base.BaseFragment
import com.zs.wanandroid.base.LazyFragment
import com.zs.wanandroid.constants.Constants
import com.zs.wanandroid.entity.IntegralEntity
import com.zs.wanandroid.http.LoginEvent
import com.zs.wanandroid.http.LogoutEvent
import com.zs.wanandroid.ui.collect.CollectActivity
import com.zs.wanandroid.ui.girl.GirlActivity
import com.zs.wanandroid.ui.login.LoginActivity
import com.zs.wanandroid.ui.rank.RankActivity
import com.zs.wanandroid.ui.set.SetActivity
import com.zs.wanandroid.ui.web.WebActivity
import com.zs.wanandroid.utils.AppManager
import com.zs.wanandroid.utils.PrefUtils
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
class MineFragment : LazyFragment<MineContract.Presenter<MineContract.View>>(),View.OnClickListener,
    MineContract.View {

    private var integralEntity: IntegralEntity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun lazyInit() {
        //先判断数据是否为空，然后再强转，否则会出异常
        PrefUtils.getObject(Constants.INTEGRAL_INFO)?.let {
            //先从本地获取积分，获取不到再通过网络获取
            integralEntity = it as IntegralEntity?
        }
        if (integralEntity==null){
            if (AppManager.isLogin()) {
                presenter?.loadIntegral()
            }
        }else{
            setIntegral()
        }
        setListener()
    }

    private fun setIntegral(){
        integralEntity?.apply {
            tvUserName.text = username
            tvId.text = String.format("%s","id:$userId")
            tvRanking.text = rank.toString()
            tvIntegral.text = coinCount.toString()
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
            R.id.llRanking->{
                intent(Bundle().apply {
                    integralEntity?.coinCount?.let { putInt(Constants.MY_INTEGRAL, it) }
                    integralEntity?.rank?.let { putInt(Constants.MY_RANK, it) }
                    integralEntity?.username?.let { putString(Constants.MY_NAME, it) }
                }, RankActivity::class.java,true)
            }
            R.id.rlIntegral->{}
            R.id.rlCollect-> intent(CollectActivity::class.java,true)
            R.id.rlArticle->{}
            R.id.rlWebsite->{
                intent(Bundle().apply {
                    putString(Constants.WEB_URL,Constants.WEBSITE)
                    putString(Constants.WEB_TITLE,Constants.APP_NAME)
                }, WebActivity::class.java,false)
            }
            R.id.rlGirl->intent(GirlActivity::class.java,false)
            R.id.rlSet-> intent(SetActivity::class.java,false)

        }
    }

    override fun showIntegral(e: IntegralEntity) {
        this.integralEntity = e
        setIntegral()

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

        //设置积分为空，下次进入界面重新请求
        PrefUtils.removeKey(Constants.INTEGRAL_INFO)
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
