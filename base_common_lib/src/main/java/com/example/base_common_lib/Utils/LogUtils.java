package com.example.base_common_lib.Utils;
import com.orhanobut.logger.Logger;

/**
 * @Author G_JS
 * 日志专用类
 * @Date Created by 2020/6/12 11:24
 *
 */
public class LogUtils {
    public static final boolean isDebug = true;

    public static void e(String log){
        if (isDebug) {
            Logger.e("异常信息:"+log);
        }
    }

    public static void d(String log){
        if (isDebug) {
//            Log.d("LoggingInterceptor", log);
       Logger.d(log);
        }
    }

    public static void i(String log){
        if (isDebug) {
//            Log.d("LoggingInterceptor", log);
          Logger.i(log);
        }
    }

    public static void el(String log){
        if (isDebug) {
            Logger.e("tag"+log);
//            Logger.e(log);
        }
    }


    public static void workResquset(String log){
        if (isDebug) {
           Logger.d("网络请求 接收"+log);
        }
    }

    public static void workPost(String log){
        if (isDebug) {
            Logger.d("网络请求 发送:"+log);
        }
    }
}
