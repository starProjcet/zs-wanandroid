package com.example.zs_wan_android.utils;

import com.example.zs_wan_android.constants.Constants;

public class AppUtils {

    /**
     * 登录状态
     */
    public static boolean isLogin(){
        return PrefUtils.INSTANCE.getBoolean(Constants.LOGIN,false);
    }
}
