package com.example.zs_wan_android.utils;

import android.widget.Toast;

import com.example.zs_wan_android.WanAndroidApplication;

/**
 * des toast工具类
 * @author zs
 * @data 2020-03-10
 */
public class ToastUtils {

    public static void show(String msg){
        Toast.makeText(WanAndroidApplication.getContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
