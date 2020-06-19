package com.example.testlib;

import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.didichuxing.doraemonkit.DoraemonKit;
import com.example.base_common_lib.Utils.Other_Utils;

/**
 * 作者：G_JS
 * 时间：
 */
public class Test_App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
            DoraemonKit.install(this);
        }
        Other_Utils.init(this);
        ARouter.init(this);
    }
}
