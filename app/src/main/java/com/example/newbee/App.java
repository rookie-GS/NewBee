package com.example.newbee;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
//            DoraemonKit.install(this);

        }
        ARouter.init(this);
    }
}
