package com.example.zs_wan_android.http;

import com.example.zs_wan_android.WanAndroidApplication;
import com.example.zs_wan_android.constants.ApiConstants;
import com.example.zs_wan_android.constants.Constants;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitFactory {

    private static OkHttpClient.Builder getOkHttpClientBuilder() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(WanAndroidApplication.getContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        return new OkHttpClient.Builder()
                .readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                // .sslSocketFactory(SslContextFactory.getSSLSocketFactoryForTwoWay())  // https认证 如果要使用https且为自定义证书 可以去掉这两行注释，并自行配制证书。
                // .hostnameVerifier(new SafeHostnameVerifier())
                .cache(cache);
    }

    public static Retrofit factory() {

        OkHttpClient okHttpClient = getOkHttpClientBuilder().build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(FilterGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConstants.BASE_URL)
                .build();
    }


}
