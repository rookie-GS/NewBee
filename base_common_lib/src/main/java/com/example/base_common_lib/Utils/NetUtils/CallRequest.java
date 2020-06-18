package com.example.base_common_lib.Utils.NetUtils;
import com.example.base_common_lib.Utils.LogUtils;
import com.example.base_common_lib.Utils.NetState;
import com.example.base_common_lib.Utils.ToastUtils;
import com.google.gson.JsonSyntaxException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;


/**
 *
 * Created by on 2017/12/29.
 *
 * @author lmy
 */

public abstract class CallRequest<T> {

    public abstract void onSuccess(T o);

    public void onError(Throwable e){
        if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
            ToastUtils.showLongToast("连接超时,请重试, 或者请联系QQ群进行帮助");
        } else if (e instanceof SocketException || e instanceof HttpException || e instanceof UnknownHostException) {
            if (!NetState.IfNet()) {
                ToastUtils.showLongToast("连接后台异常");
            }
        } else if (e instanceof JsonSyntaxException){
            ToastUtils.showLongToast("解析异常, 请联系QQ群反馈");
            LogUtils.e("JsonSyntaxException:"+e.toString());
        }else {
            String code = e.getLocalizedMessage();
            if (code != null && code.equals("2")) {
                String message = e.getMessage();
                ToastUtils.showShortToast(message == null ? "未知异常,请联系QQ群反馈" : message);
            }else {
                ToastUtils.showShortToast("未知异常,请联系QQ群反馈");
            }
        }
    }
}
