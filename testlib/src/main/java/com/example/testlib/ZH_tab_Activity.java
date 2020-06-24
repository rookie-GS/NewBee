package com.example.testlib;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseActivity;
import com.example.base_common_lib.Constant;
import com.example.base_common_lib.Utils.GlideUtils;
import com.example.base_common_lib.bean.Demo_list_bean;

import java.util.ArrayList;

@Route(path = Arouter_path.TEST_ZH_PAGE)
public class ZH_tab_Activity extends BaseActivity {
    RecyclerView rv_demo;
    Demo_list_adapter adapter;
    ArrayList<Demo_list_bean> list_beans = new ArrayList<>();
    ImageView iv_one,iv_two,iv_three,iv_four;
    @Override
    protected boolean setToolbvis() {
        return false;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_z_h_tab;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        iv_one = findViewById(R.id.iv_one);
        iv_two = findViewById(R.id.iv_two);
        iv_three = findViewById(R.id.iv_three);
        iv_four = findViewById(R.id.iv_four);
        rv_demo = findViewById(R.id.rv_demo);
        rv_demo.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Demo_list_adapter(list_beans);

        rv_demo.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        for (int i = 0; i <15 ; i++) {
            Demo_list_bean bean = new Demo_list_bean();
            bean.setName("测试"+i);
            list_beans.add(bean);
        }
        adapter.notifyDataSetChanged();

        GlideUtils.load_circle_image(iv_one, Constant.kobe_image_url);
        GlideUtils.load_circle_image(iv_two,Constant.kobe_image_url);
        GlideUtils.load_circle_image(iv_three,Constant.luka_image_url);
        GlideUtils.load_circle_image(iv_four,Constant.luka_image_url);

    }
}