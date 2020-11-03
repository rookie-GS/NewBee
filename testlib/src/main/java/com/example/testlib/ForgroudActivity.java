package com.example.testlib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
@Route(path = Arouter_path.TEST_FORGROUND_PAGE)
public class ForgroudActivity extends BaseTitleActivity {
    CardView cv_open,cv_close;
    @Override
    protected int getContentView() {
        return R.layout.activity_forgroud;
    }

    @Override
    protected String setTextTitle() {
        return "前台服务页面";
    }
    @Override
    protected int setTitlecolor() {
        return R.color.black;
    }
    @Override
    protected boolean setToolbvis() {
        return false;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        cv_open = findViewById(R.id.cv_open);
        cv_close =findViewById(R.id.cv_close);
        cv_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start=new Intent (ForgroudActivity.this,MyForegroundService.class);
                if(Build.VERSION.SDK_INT>=26){
                    startForegroundService (start);
                }else{
                    startService (start);
                }
            }
        });
        cv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stop=new Intent (ForgroudActivity.this,MyForegroundService.class);
                stopService(stop);
            }
        });
    }

    @Override
    protected void initData() {

    }

}