package com.example.base_common_lib.Base.BaseActivity;
import android.os.Bundle;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base_common_lib.Utils.ActivityManger;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

/**
 * @Author G_JS
 *
 * @Date Created by 2020/6/11 10:37 
 * 
 */
public class SuperActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManger.getInstance().addActivity(this);
        ARouter.getInstance().inject(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManger.getInstance().removeActivity(this);
    }
}
