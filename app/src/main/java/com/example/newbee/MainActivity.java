package com.example.newbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base_common_lib.Arouter_path;
import com.example.homelib.HomeActivity;
import com.example.testlib.DemoMainActivity;

public class MainActivity extends AppCompatActivity {
    TextView tv_home,tv_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_home = findViewById(R.id.tv_home);
        tv_test = findViewById(R.id.tv_test);
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
}