package com.zs.wanandroid.utils;


import com.zs.wanandroid.constants.Constants;

import java.util.HashSet;

public class AppUtils {

    /**
     * 登录状态
     */
    public static boolean isLogin(){
        return PrefUtils.INSTANCE.getBoolean(Constants.LOGIN,false);
    }

    /**
     * 退出登录，重置用户状态
     */
    public static void resetUser(){
        PrefUtils.INSTANCE.setBoolean(Constants.LOGIN,false);
        PrefUtils.INSTANCE.setHashSet(Constants.COOKIE,new HashSet<>());
    }
}
