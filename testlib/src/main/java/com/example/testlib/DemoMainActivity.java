package com.example.testlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
import com.example.base_common_lib.Utils.LogUtils;

@Route( path = Arouter_path.TEST_HOME_PAGE)
public class DemoMainActivity extends BaseTitleActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_main_demo;
    }

    @Override
    protected String setTextTitle() {
        return "测试页面";
    }

    @Override
    protected int setToolbgcolor() {
        return R.color.white;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        LogUtils.e("测试日志：测试页");

    }

    @Override
    protected void initData() {

    }
}
