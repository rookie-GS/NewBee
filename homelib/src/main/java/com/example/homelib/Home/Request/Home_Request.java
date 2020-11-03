package com.example.homelib.Home.Request;


import android.content.Context;

import com.example.base_common_lib.Base.BaseModel.BaseHttpResult;
import com.example.base_common_lib.Utils.LogUtils;
import com.example.base_common_lib.Utils.NetUtils.Api;
import com.example.base_common_lib.Utils.NetUtils.HttpUtil;
import com.example.base_common_lib.Utils.NetUtils.ProgressSubscriber;
import com.example.base_common_lib.bean.ResultBean;
import com.google.gson.Gson;
import com.trello.rxlifecycle4.LifecycleTransformer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;


public class Home_Request {

    public void getHome_data(final Home_data_pre_lisener lisener){
        LogUtils.e("mac_git_test");
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("page",1);
        map.put("count",5);
        Observable<BaseHttpResult<List<ResultBean>>> home_request = Api.getDefault().get_sina_new(Api.postJson(new Gson().toJson(map)));
        HttpUtil.getInstance()
                .getBuilder()
                .create(home_request)
                .cacheData("homedata")
                .bindLifecycle(lisener.lifecycleSubject())
                .subscriber(new ProgressSubscriber<List<ResultBean>>() {
                    @Override
                    protected void onLoadSuccess(List<ResultBean> o) {
                        lisener.onRequest_success(o);
                    }

                    @Override
                    protected void onLoadError(String message) {
                        LogUtils.e("新浪新闻:"+message);
                        lisener.onRequestErr(message);
                    }
                });

    }

    public interface Home_data_pre_lisener {
        void onRequest_success(List<ResultBean> o);

        void onRequestErr(String err);

        Context getContexts();

        LifecycleTransformer lifecycleSubject();

    }
}
