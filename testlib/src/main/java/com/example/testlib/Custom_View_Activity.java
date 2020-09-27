package com.example.testlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;

@Route(path = Arouter_path.TEST_CUSTOM_VIEW_PAGE )
public class Custom_View_Activity extends BaseTitleActivity {
    @Override
    protected String setTextTitle() {
        return "自定义View页面";
    }
    @Override
    protected int setTitlecolor() {
        return R.color.black;
    }
    @Override
    protected boolean setToolbvis() {
        return true;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_custom__view;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }
}