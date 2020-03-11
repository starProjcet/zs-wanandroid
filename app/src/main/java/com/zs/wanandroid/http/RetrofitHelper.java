package com.zs.wanandroid.http;

public class RetrofitHelper {
    private static ApiService apiService;
    private RetrofitHelper(){}
    public static ApiService getApiService(){
        return apiService;
    }
    static {
        apiService= RetrofitFactory.factory().create(ApiService.class);
    }
}
