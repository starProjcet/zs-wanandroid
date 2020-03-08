package com.example.zs_wan_android.http;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import retrofit2.Converter;
import okhttp3.ResponseBody;

/**
 * 将Response进行脱壳
 * 过滤服务器信息，统一处理业务code
 *
 * @author zs
 * @data 2020-03-07
 */
public class FilterGsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {

    private final TypeAdapter<T> adapter;

    FilterGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        // 这里就是对返回结果进行处理
        String jsonString = value.string();
        try {
            JSONObject object = new JSONObject(jsonString);
            int code = object.getInt("errorCode");
            if (code==0) {
                return adapter.fromJson(object.getString("data"));
            } else {
                filterCode(object.getString("errorMsg"),code);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new BusinessHttpException("数据解析异常",-1);
        }finally {
            value.close();
        }
        return null;
    }

    private void filterCode(String msg,int code) throws BusinessHttpException {
        //特定 API 的错误，在相应的 DefaultObserver 的 onError 的方法中进行处理
        //throw new HttpException(msg, code);
        switch (code){
            //登录手失败
            case -1001:
                break;
            default:
                throw new BusinessHttpException(msg, code);
        }
    }
}
