package com.example.homelib.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.didichuxing.doraemonkit.DoraemonKit;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseMVPActivity;
import com.example.base_common_lib.Utils.SpannableStringUtils;
import com.example.base_common_lib.Utils.ToastUtils;
import com.example.base_common_lib.bean.ResultBean;
import com.example.homelib.Home.Presenter.MainPreSenter;
import com.example.homelib.Home.View.IHomeView;
import com.example.homelib.R;
import com.trello.rxlifecycle4.LifecycleTransformer;
import com.trello.rxlifecycle4.android.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author G_JS
 * 
 * @Date Created by 2020/6/11 16:48 
 * 
 */

@Route( path = Arouter_path.HOME_PAGE)
public class HomeActivity extends BaseMVPActivity<IHomeView, MainPreSenter> implements IHomeView,View.OnClickListener {


    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }
    @Override
    protected String setTextTitle() {
        return "首页";
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
        return false;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {


    }




    @Override
    public Context onContext() {
        return mContext;
    }

    @Override
    public LifecycleTransformer onBindToLifecycle() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

    @Override
    public void onError() {
        nextErrPager();
    }
    @Override
    public MainPreSenter createPresent() {
        return new MainPreSenter(this);
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRequestSuccess(List<ResultBean> contentBeanList) {

    }


}
