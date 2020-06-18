package com.example.homelib.Home.Presenter;

import android.content.Context;

import com.example.base_common_lib.Base.BasePresenter;
import com.example.base_common_lib.bean.ResultBean;
import com.example.homelib.Home.Request.Home_Request;
import com.example.homelib.Home.View.IHomeView;
import com.trello.rxlifecycle4.LifecycleTransformer;

import java.util.List;


/**
 * @Author G_JS
 *
 * @Date Created by 2020/6/12 13:51
 *
 */
public class MainPreSenter extends BasePresenter<IHomeView> {
    IHomeView homeView;
    private Home_Request request;
    private  Context mContext;

    public MainPreSenter(IHomeView homeView) {
        request = new Home_Request();
        this.homeView = homeView;
        mContext = homeView.onContext();

    }

    @Override
    public void requestData() {
        request.getHome_data(new Home_Request.Home_data_pre_lisener() {
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
