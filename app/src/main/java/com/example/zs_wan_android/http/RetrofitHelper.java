package com.example.zs_wan_android.http;

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
