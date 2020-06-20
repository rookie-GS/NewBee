package com.example.testlib.Net_Demo;

import android.content.Context;

import com.example.base_common_lib.Base.BasePresenter;
import com.example.base_common_lib.bean.ResultBean;
import com.trello.rxlifecycle4.LifecycleTransformer;

import java.util.List;

public class NetPreSenter extends BasePresenter<Net_View> {
    Net_View homeView;
    private Net_Request request;
    private Context mContext;

    public NetPreSenter(Net_View homeView) {
        request = new Net_Request();
        this.homeView = homeView;
        mContext = homeView.onContext();

    }

    @Override
    public void requestData() {
        request.getNet_data(new Net_Request.Home_data_pre_lisener() {
            @Override
            public void onRequest_success(List<ResultBean> o) {
                homeView.onRequestSuccess(o);

            }

            @Override
            public LifecycleTransformer lifecycleSubject() {
                return homeView.onBindToLifecycle();
            }

            @Override
            public Context getContexts() {
                return mContext;
            }

            @Override
            public void onRequestErr(String err) {
                homeView.onError();
            }
        });
    }
}
