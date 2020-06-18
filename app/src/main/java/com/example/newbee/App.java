package com.example.newbee;
import androidx.multidex.MultiDexApplication;
import com.alibaba.android.arouter.launcher.ARouter;
import com.didichuxing.doraemonkit.DoraemonKit;
import com.example.base_common_lib.Utils.Other_Utils;
import com.example.base_common_lib.Utils.ToastUtils;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


public class App extends MultiDexApplication {
    public static final boolean isDebug = BuildConfig.DEBUG;
    @Override
    public void onCreate() {
        super.onCreate();
        if(isDebug){
            ARouter.openLog();
            ARouter.openDebug();
            DoraemonKit.install(this);
            Logger.addLogAdapter(new AndroidLogAdapter());
        }
        //工具类初始化
        Other_Utils.init(this);
        ARouter.init(this);
        ToastUtils.init(true);  //吐司初始化
        //网络缓存初始化
        Hawk.init(Other_Utils.getContext()).build();

    }
}
