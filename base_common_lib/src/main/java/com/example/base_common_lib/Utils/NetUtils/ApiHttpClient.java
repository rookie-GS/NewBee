package com.example.base_common_lib.Utils.NetUtils;
import com.example.base_common_lib.Utils.LogUtils;
import com.example.base_common_lib.Utils.Other_Utils;

import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @Author G_JS
 *
 * @Date Created by 2020/6/12 13:51
 *
 */
public class ApiHttpClient {

    private static final long DEFAULT_TIMEOUT = 10000;
    /**
     * 功能参数 客户端标识
     */
    private static final String PLATFORM = "0";
    /**
     * 功能参数 APP版本
     */
    private static final String SYSTEM = Other_Utils.getSystemVersion();
    /**
     * 接口版本控制
     */
    private static final String API_VERSION = "4";

    /**
     * 设置httpClient
     */
    static OkHttpClient.Builder httpClient() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        clientBuilder(httpClientBuilder);
        return httpClientBuilder;
    }

    static OkHttpClient.Builder httpClient(long tiem) {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder
                .connectTimeout(tiem, TimeUnit.MILLISECONDS)
                .readTimeout(tiem, TimeUnit.MILLISECONDS)
                .writeTimeout(tiem, TimeUnit.MILLISECONDS);
        clientBuilder(httpClientBuilder);
        return httpClientBuilder;
    }

    private static void clientBuilder(OkHttpClient.Builder httpClientBuilder) {
        if (LogUtils.isDebug) {
            //使用自定义的Log拦截器
            //设置 Debug Log 模式
            httpClientBuilder.addInterceptor(new LoggingInterceptor());
        }
        //进度
        httpClientBuilder.addInterceptor(new ProgressInterceptor());

        //拦截器
        httpClientBuilder.addInterceptor(chain -> {
            Request request = chain.request();
            HttpUrl.Builder authorizedUrlBuilder = request.url()
                    .newBuilder();
                    //添加统一参数 如手机唯一标识符,token等
//                 .addQueryParameter("platform", PLATFORM)
//                    .addQueryParameter("system", SYSTEM)
//                    .addQueryParameter("software", Other_Utils.getVersionName())
//                    .addQueryParameter("dataAccountId", SPUtils.getAccountId())
//                    .addQueryParameter("ver", API_VERSION)
//                    //手机型号
//                    .addQueryParameter("phoneModel", SystemUtil.getSystemModel())

            Request newRequest = request.newBuilder()
                    //对所有请求添加请求头
//                            .header("mobileFlag", "adfsaeefe")
//                            .addHeader("type", "4")
                    .method(request.method(), request.body())
                    .url(authorizedUrlBuilder.build())
                    .build();

            return chain.proceed(newRequest);
        });
    }
}
