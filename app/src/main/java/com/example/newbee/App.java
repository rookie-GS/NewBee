package com.example.newbee;
import androidx.multidex.MultiDexApplication;
import com.alibaba.android.arouter.launcher.ARouter;
import com.didichuxing.doraemonkit.DoraemonKit;
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
        ARouter.init(this);
    }
}
