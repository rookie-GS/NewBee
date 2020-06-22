package com.example.testlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseActivity;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
import com.example.base_common_lib.Utils.ToastUtils;
import com.google.android.material.navigation.NavigationView;
/**
 * @Author G_JS
 * 
 * @Date Created by 2020/6/22 16:41 
 * 
 */
@Route( path = Arouter_path.TEST_LEFT_PAGE)
public class Nagvion_Activity extends BaseTitleActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    TextView tv_out;

    @Override
    protected boolean setToolbvis() {

        //toolbar 显示与否
        return false;
    }

    @Override
    protected String setTextTitle() {
        return "侧拉界面测试";
    }
    @Override
    protected int setTitlecolor() {
        return R.color.black;
    }
    @Override
    protected int setToolbgcolor() {
        return R.color.white;
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_nagvion;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tv_out = findViewById(R.id.tv_out);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        tv_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT,true);
            }
        });
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                String title = item.getTitle().toString();
                ToastUtils.showShortToast(title);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    protected void initData() {

    }
}