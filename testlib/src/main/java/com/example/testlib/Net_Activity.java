package com.example.testlib;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseMVPActivity;
import com.example.base_common_lib.bean.ResultBean;
import com.example.testlib.Net_Demo.NetPreSenter;
import com.example.testlib.Net_Demo.Net_View;
import com.trello.rxlifecycle4.LifecycleTransformer;
import com.trello.rxlifecycle4.android.ActivityEvent;

import java.util.List;

@Route( path = Arouter_path.TEST_NET_PAGE)
public class Net_Activity extends BaseMVPActivity<Net_View, NetPreSenter> implements Net_View {

    Button bt_get_net,bt_clean_net;
    TextView tv_net_body;
    @Override
    protected String setTextTitle() {
        return "网络请求测试";
    }

    @Override
    protected int setToolbgcolor() {
        return R.color.white;
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
    public NetPreSenter createPresent() {
        return new NetPreSenter(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_net;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        bt_get_net = findViewById(R.id.bt_get_net);
        tv_net_body = findViewById(R.id.tv_net_body);
        bt_clean_net = findViewById(R.id.bt_clean_net);
        bt_clean_net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_net_body.setText("");
            }
        });
        bt_get_net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.requestData();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRequestSuccess(List<ResultBean> contentBeanList) {
        String str = "";
        for (int i = 0; i <contentBeanList.size() ; i++) {
            ResultBean bean = contentBeanList.get(i);
            str = str+bean.getTitle()+"\n\n\n";
        }
        tv_net_body.setText(str);
    }

    @Override
    public Context onContext() {
        return mContext;
    }

    @Override
    public LifecycleTransformer onBindToLifecycle() {
        return bindUntilEvent(ActivityEvent.DESTROY) ;
    }

    @Override
    public void onError() {
        tv_net_body.setText("请检查网络");

    }
}