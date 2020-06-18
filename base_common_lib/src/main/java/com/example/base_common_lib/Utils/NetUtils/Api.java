package com.example.base_common_lib.Utils.NetUtils;
import com.example.base_common_lib.Utils.LogUtils;
import com.example.base_common_lib.Utils.NetUtils.converter.CustomGsonConverterFactory;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;


public class Api extends ApiHttpClient {
    //普通请求
    private static ApiService apiService;
    //广告
    private static ApiService apiAdsService;
    //新版登录
    private static ApiService loginApiService;

    private static String NORMAL_HOST = "";

    private static String LOGIN_HOST = "";

    private static String OTHER_HOST = "";
    /**
     * 常规请求
     * */
    public static ApiService getDefault() {
        if(LogUtils.isDebug){
            NORMAL_HOST = "https://api.apiopen.top/";
        }else {
            NORMAL_HOST = " https://api.apiopen.top/";
        }
        if (apiService == null) {
            apiService = new Retrofit.Builder()
                    .client(httpClient().build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(CustomGsonConverterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(NORMAL_HOST)
                    .build()
                    .create(ApiService.class);
        }
        return apiService;
    }
    /**
     * 登录的请求
     * */
    public static ApiService getLoginDefault() {
        if(LogUtils.isDebug){
            LOGIN_HOST = "";
        }else {
            LOGIN_HOST = "";
        }
        if (loginApiService == null) {
            loginApiService = new Retrofit.Builder()
                    .client(httpClient().build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(CustomGsonConverterFactory.create())
                    .baseUrl(LOGIN_HOST)
                    .build()
                    .create(ApiService.class);
        }
        return loginApiService;
    }

    /**
     * 可设置超时时长的请求
     * */
    public static ApiService getDefault(long time) {
        if(LogUtils.isDebug){
            OTHER_HOST = "";
        }else {
            OTHER_HOST = "";
        }
        if (apiAdsService == null) {
            apiAdsService = new Retrofit.Builder()
                    .client(httpClient(time).build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(CustomGsonConverterFactory.create())
                    .baseUrl(OTHER_HOST)
                    .build()
                    .create(ApiService.class);
        }
        return apiAdsService;
    }





    public static RequestBody postJson(String json) {
        return RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);
    }
}
