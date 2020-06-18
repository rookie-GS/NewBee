package com.example.base_common_lib.Utils;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.example.base_common_lib.Arouter_path;

@Interceptor(priority = 7)
public class AR_Intercapter implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {

        if(postcard.getExtra() == Arouter_path.LOGIN_AR) {
            LogUtils.e("拦截到的参数:"+postcard.getExtra());
        }
            callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {

    }
}
