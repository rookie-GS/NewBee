package com.example.base_common_lib.Base.BaseActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.base_common_lib.R;
import com.example.base_common_lib.Utils.ActivityLifeCycleEvent;
import com.example.base_common_lib.Utils.Other_Utils;

import io.reactivex.subjects.PublishSubject;


/**
 * @Author G_JS
 *
 * @Date Created by 2020/6/11 10:37
 *
 */

public class SuperTopbarBaseActivity extends SuperActivity {
    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();
    public Toolbar toolbar;

    public ViewStub title_view_stub;
    public Context mContext;
    /**当前页面的父布局*/
    protected FrameLayout viewContent;
    /**当前页面标题*/
    protected TextView tvTitle;
    /**下滑线*/
    protected View superViewLin;
    /**title覆盖叠加*/
    protected SwipeRefreshLayout superSwipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (!isSuperFragmentLayout()) {
            setContentView(R.layout.activity_base_top_bar);
        }else {
            //使用父布局为 FrameLayout的xml
            setContentView(R.layout.activity_base_top_bar_frlayout);
        }

        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
        //loadViewStub
        initViewStub();
        initView();
        //初始化titleBar
        initTitleBar();
        setContentViews(savedInstanceState);
        //其他配置
        setAdditionConfigure();

    }

    protected void initViewStub() {

    }

    protected void initTitleBar() {
    }

    private void initView() {
        toolbar =  findViewById(R.id.toolbar);
        viewContent =  findViewById(R.id.viewContent);
        tvTitle =  findViewById(R.id.tvTitle);
        superViewLin = findViewById(R.id.super_view_lin);
        title_view_stub = findViewById(R.id.title_view_stub);
        if (toolbar != null) {
            if (!isSuperFragmentLayout()) {
                toolbar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Other_Utils.dip2px(45)));
            }else {
                superSwipeRefresh = findViewById(R.id.swipe_refresh);
                superViewLin.setVisibility(View.GONE);
            }
        }
    }

    protected void setAdditionConfigure() {
    }

    protected void setContentViews(Bundle savedInstanceState) {

    }

    /**
     * 父布局是否是fragmentLayout
     * @return 默认false
     */
    protected boolean isSuperFragmentLayout(){
        return false;
    }


    protected ViewStub getViewStub() {
        return (ViewStub) findViewById(R.id.tl_view_stub);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycleSubject.onNext(ActivityLifeCycleEvent.PAUSE);
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY);
//        ARouter.getInstance().destroy();
    }

//    @Override
//    public Resources getResources() {
//        Resources res = super.getResources();
//        Configuration config=new Configuration();
//        config.setToDefaults();
//        res.updateConfiguration(config,res.getDisplayMetrics());
////        LogUtils.el("getResources()");
//        return res;
//    }

    @Override
    public Resources getResources() {
        //需要升级到 v1.1.2 及以上版本才能使用 AutoSizeCompat
//        AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources()));//如果没有自定义需求用这个方法
//        AutoSizeCompat.autoConvertDensity((super.getResources(), 667, false);//如果有自定义需求就用这个方法
        return super.getResources();
    }
}
