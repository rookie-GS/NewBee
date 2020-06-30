package com.example.newbee;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseActivity;
import com.example.base_common_lib.Base.CommonAdapter;
import com.example.base_common_lib.Utils.ToastUtils;
import com.example.base_common_lib.bean.Demo_list_bean;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    public static final long FIN_TIME = 3000;
    public long clickTime = 0;
    RecyclerView rv_main;
    CommonAdapter  commonAdapter;
    ArrayList<Demo_list_bean> mList = new ArrayList<>();
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        rv_main = findViewById(R.id.rv_main);
        rv_main.setLayoutManager(new LinearLayoutManager(this));
        commonAdapter = new CommonAdapter(mList);
        rv_main.setAdapter(commonAdapter);
        commonAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                ARouter.getInstance().build(mList.get(position).getArouter()).navigation();
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
        commonAdapter.notifyDataSetChanged();
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