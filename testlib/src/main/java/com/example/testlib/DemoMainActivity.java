package com.example.testlib;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
import com.example.base_common_lib.Base.CommonAdapter;
import com.example.base_common_lib.Utils.LogUtils;
import com.example.base_common_lib.bean.Demo_list_bean;
import com.github.ihsg.demo.util.PatternHelper;

import java.util.ArrayList;

@Route( path = Arouter_path.TEST_HOME_PAGE)
public class DemoMainActivity extends BaseTitleActivity implements View.OnClickListener{
    RecyclerView rv_list;
    CommonAdapter demo_list_adapter;
    ArrayList<Demo_list_bean> mList = new ArrayList<>();
    @Override
    protected int getContentView() {
        return R.layout.activity_main_demo;
    }

    @Override
    protected boolean setToolbvis() {
        return true;
    }

    @Override
    protected String setTextTitle() {
        return "选择测试项目";
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
    protected void init(Bundle savedInstanceState) {
        LogUtils.e("测试日志：测试页");
        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        demo_list_adapter = new CommonAdapter(mList);
        rv_list.setAdapter(demo_list_adapter);
        demo_list_adapter.setOnItemClickListener((adapter, view, position) -> {
            if(mList.get(position).getArouter().equals(Arouter_path.TEST_SETLOCKING_PAGE)){
                if(new PatternHelper().having_pwd()){
                    ARouter.getInstance().build(Arouter_path.TEST_LOCKING_PAGE).navigation();
                }else {
                    ARouter.getInstance().build(Arouter_path.TEST_SETLOCKING_PAGE).navigation();
                }
            }else {
                ARouter.getInstance().build(mList.get(position).getArouter()).navigation();
            }
        });
    }

    @Override
    protected void initData() {
        mList.clear();
        Demo_list_bean bean = new Demo_list_bean();
        bean.setName("弹窗测试");
        bean.setArouter(Arouter_path.TEST_DIALOG_PAGE);
        mList.add(bean);

        Demo_list_bean bean_2 = new Demo_list_bean();
        bean_2.setName("图片测试");
        bean_2.setArouter(Arouter_path.TEST_IMAGE_PAGE);
        mList.add(bean_2);

        Demo_list_bean bean_3 = new Demo_list_bean();
        bean_3.setName("字符串拼接测试");
        bean_3.setArouter(Arouter_path.TEST_STRING_PAGE);
        mList.add(bean_3);

        Demo_list_bean bean_4 = new Demo_list_bean();
        bean_4.setName("网络请求测试");
        bean_4.setArouter(Arouter_path.TEST_NET_PAGE);
        mList.add(bean_4);

        Demo_list_bean bean_5 = new Demo_list_bean();
        bean_5.setName("侧拉抽屉测试");
        bean_5.setArouter(Arouter_path.TEST_LEFT_PAGE);
        mList.add(bean_5);
        demo_list_adapter.notifyDataSetChanged();

        Demo_list_bean bean_6 = new Demo_list_bean();
        bean_6.setName("活动底部栏测试");
        bean_6.setArouter(Arouter_path.TEST_ZH_PAGE);
        mList.add(bean_6);
        Demo_list_bean bean_7 = new Demo_list_bean();
        bean_7.setName("图片选择器测试");
        bean_7.setArouter(Arouter_path.TEST_PIC_PAGE);
        mList.add(bean_7);
        Demo_list_bean bean_8 = new Demo_list_bean();
        bean_8.setName("二维码测试");
        bean_8.setArouter(Arouter_path.TEST_QR_PAGE);
        mList.add(bean_8);
        Demo_list_bean bean_9 = new Demo_list_bean();
        bean_9.setName("自定义view测试");
        bean_9.setArouter(Arouter_path.TEST_CUSTOM_VIEW_PAGE);
        mList.add(bean_9);
        Demo_list_bean bean_10 = new Demo_list_bean();
        bean_10.setName("自定义指示器测试");
        bean_10.setArouter(Arouter_path.TEST_CUSTOM_INDECOTOR_PAGE);
        mList.add(bean_10);
        Demo_list_bean bean_11 = new Demo_list_bean();
        bean_11.setName("圆形进度条测试");
        bean_11.setArouter(Arouter_path.TEST_CIRCLE_BOTTOM_PAGE);
        mList.add(bean_11);
        Demo_list_bean bean_12 = new Demo_list_bean();
        bean_12.setName("设置解锁码");
        bean_12.setArouter(Arouter_path.TEST_SETLOCKING_PAGE);
        mList.add(bean_12);
        demo_list_adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }
}
