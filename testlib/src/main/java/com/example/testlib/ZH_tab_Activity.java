package com.example.testlib;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseActivity;
import com.example.base_common_lib.bean.Demo_list_bean;

import java.util.ArrayList;

@Route(path = Arouter_path.TEST_ZH_PAGE)
public class ZH_tab_Activity extends BaseActivity {
    RecyclerView rv_demo;
    Demo_list_adapter adapter;
    ArrayList<Demo_list_bean> list_beans = new ArrayList<>();
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
    }
}