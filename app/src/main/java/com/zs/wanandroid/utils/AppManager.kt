package com.zs.wanandroid.utils

import com.zs.wanandroid.constants.Constants
import java.util.HashSet



/**
 * des app管理类
 *
 * @author zs
 * @data 2020-03-12
 */
class AppManager {
    companion object{
        /**
         * 登录状态
         */
        fun isLogin():Boolean {
            return PrefUtils.getBoolean(Constants.LOGIN, false)
        }

        /**
         * 退出登录，重置用户状态
         */
        fun resetUser() {
            PrefUtils.setBoolean(Constants.LOGIN, false)
            PrefUtils.setHashSet(Constants.COOKIE, HashSet())
            PrefUtils.setObject(Constants.USER_INFO, null)
        }
    }
}