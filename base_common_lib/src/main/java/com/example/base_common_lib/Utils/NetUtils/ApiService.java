package com.example.base_common_lib.Utils.NetUtils;
import com.example.base_common_lib.Base.BaseModel.BaseHttpResult;
import com.example.base_common_lib.bean.ResultBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 定义接口地址
 * Created by lmy on 2017/7/11
 */

public interface ApiService {
    /**
     * 下载
     */
    @Streaming
    @GET
    Observable<ResponseBody> downFile(@Url String url);

    /**
     * 断点下载
     *
     * @param startByte 开始字节下载
     * @param url       下载链接
     */
    @Streaming
    @GET
    Observable<ResponseBody> downFile(@Header("RANGE") String startByte, @Url String url);


    /**
     * 获取手机验证码
     *
     * @return
     */
    @GET("account/getSmsCode")
    Observable<BaseHttpResult> getPhoneCode(@QueryMap() Map<String, String> map);

//    /**
//     * 获取粉丝列表
//     */
//    @GET("userFollowUser/queryAllFansById")
//    Observable<BaseHttpResult<List<MyAiTeBean>>> queryAllFansById(@QueryMap() Map<String, String> map);
    /**
     * 节拍用户相关
     */
    @GET("audio/checkUseBeatCount")
    Observable<BaseHttpResult<Object>> checkUseBeatCount(@Query("accountId") String accountId
            , @Query("remainCount") String remainCount);
    /**
     * 网易新闻
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("getWangYiNews")
    Observable<BaseHttpResult<List<ResultBean>>> get_sina_new(@Body RequestBody requestBody);

//    /***
//     * 解绑三方账户
//     */
//    @POST("login/checkLoginStatus")
//    Observable<BaseHttpResult<LoginCheckStatus>> checkLoginStatus(@Body RequestBody requestBody);

}
