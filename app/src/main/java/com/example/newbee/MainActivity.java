package com.example.newbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseActivity;
import com.example.base_common_lib.Utils.LogUtils;
import com.example.base_common_lib.Utils.MiuiDeviceUtil;
import com.example.base_common_lib.Utils.Other_Utils;
import com.example.base_common_lib.Utils.ToastUtils;
import com.example.base_common_lib.bean.Demo_list_bean;
import com.example.testlib.Demo_list_adapter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    public static final long FIN_TIME = 3000;
    public long clickTime = 0;
    RecyclerView rv_main;
    Demo_list_adapter adapter;
    ArrayList<Demo_list_bean> mList = new ArrayList<>();
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        rv_main = findViewById(R.id.rv_main);
        rv_main.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Demo_list_adapter(mList);
        rv_main.setAdapter(adapter);
        adapter.setItemClick(new Demo_list_adapter.ItemClicklisener() {
            @Override
            public void ItemOnclick(String router) {
                ARouter.getInstance().build(router).navigation();

            }
        });
    }


    @Override
    protected void initData() {
        Demo_list_bean bean = new Demo_list_bean();
        bean.setName("Home模块(暂空)");
        bean.setArouter(Arouter_path.HOME_PAGE);
        mList.add(bean);
        Demo_list_bean bean1 = new Demo_list_bean();
        bean1.setName("集成测试模块");
        bean1.setArouter(Arouter_path.TEST_HOME_PAGE);
        mList.add(bean1);
        adapter.notifyDataSetChanged();
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