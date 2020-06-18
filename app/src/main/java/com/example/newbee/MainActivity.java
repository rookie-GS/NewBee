package com.example.newbee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseActivity;
import com.example.base_common_lib.Utils.LogUtils;
import com.example.base_common_lib.Utils.ToastUtils;

public class MainActivity extends BaseActivity {
    TextView tv_home,tv_test;
    public static final long FIN_TIME = 3000;
    public long clickTime = 0;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tv_home = findViewById(R.id.tv_home);
        tv_test = findViewById(R.id.tv_test);
        LogUtils.e("测试日志：入口页");
        tv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Arouter_path.HOME_PAGE).navigation();
            }
        });
        tv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Arouter_path.TEST_HOME_PAGE).navigation();
            }
        });
    }

    @Override
    protected void initData() {

    }
    @Override
    public void onBackPressed() {

        long startOneTime = System.currentTimeMillis();
        if (startOneTime - clickTime < FIN_TIME) {
            super.onBackPressed();
        } else {
            ToastUtils.showShortToast("再次点击退出");
        }
        clickTime = startOneTime;
    }
}