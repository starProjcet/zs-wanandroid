package com.zs.wanandroid.utils;

import com.android.constants.Constants;

public class AppUtils {

    /**
     * 登录状态
     */
    public static boolean isLogin(){
        return PrefUtils.INSTANCE.getBoolean(Constants.LOGIN,false);
    }
}
