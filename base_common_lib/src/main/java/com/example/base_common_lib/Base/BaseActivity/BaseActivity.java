package com.example.base_common_lib.Base.BaseActivity;

import android.os.Bundle;

/**
 * @Author G_JS
 * 其他子类activity继承BaseActivity  不带有titleBar
 * @Date Created by 2020/6/11 14:50 
 * 
 */
public abstract class BaseActivity extends SuperInitActivity {

    @Override
    public void setContentViews(Bundle savedInstanceState) {
        super.setContentViews(savedInstanceState);

    }

}
