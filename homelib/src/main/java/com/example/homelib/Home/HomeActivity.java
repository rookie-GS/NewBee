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
    TextView tv_message,tv_net_text;
    ImageView iv_pic,iv_pic02,iv_pic03,iv_pic04;

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }
    @Override
    protected String setTextTitle() {
        return "资讯";
    }

    @Override
    protected int setToolbgcolor() {
        return R.color.red;
    }

    @Override
    protected boolean setToolbvis() {
        return false;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void init(Bundle savedInstanceState) {
        tv_net_text = findViewById(R.id.tv_net_text);
        tv_message = findViewById(R.id.tv_message);
        iv_pic = findViewById(R.id.iv_pic);
        iv_pic02 = findViewById(R.id.iv_pic02);
        iv_pic03 = findViewById(R.id.iv_pic03);
        iv_pic04 = findViewById(R.id.iv_pic04);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.color.black);
        requestOptions.placeholder(R.color.black);
        requestOptions.transform(new CircleCrop());
        Glide.with(this)
                .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=245785532,241083071&fm=26&gp=0.jpg")
                .apply(requestOptions)
                .into(iv_pic);
        RequestOptions requestOptions02 = new RequestOptions();
        requestOptions02.placeholder(R.color.black);
        requestOptions02.placeholder(R.color.black);
        requestOptions02.transform(new RoundedCorners(20));
        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592283318054&di=03e6ab9aad4a285cc567cb4b09e06403&imgtype=0&src=http%3A%2F%2Ffile05.16sucai.com%2F2015%2F0710%2F0146fbc40ac9b6e8d8d5f0a33ba54d7b.jpg")
                .apply(requestOptions02)
                .into(iv_pic02);
        RequestOptions requestOptions03 = new RequestOptions();
        requestOptions03.placeholder(R.color.black);
        requestOptions03.placeholder(R.color.black);
        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592283318054&di=03e6ab9aad4a285cc567cb4b09e06403&imgtype=0&src=http%3A%2F%2Ffile05.16sucai.com%2F2015%2F0710%2F0146fbc40ac9b6e8d8d5f0a33ba54d7b.jpg")
                .apply(requestOptions03)
                .transition(DrawableTransitionOptions.withCrossFade(2000)) // 渐变
                .into(iv_pic03);
        RequestOptions requestOptions04 = new RequestOptions();
        requestOptions04.placeholder(R.color.black);
        requestOptions04.placeholder(R.color.black);
        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592283318054&di=03e6ab9aad4a285cc567cb4b09e06403&imgtype=0&src=http%3A%2F%2Ffile05.16sucai.com%2F2015%2F0710%2F0146fbc40ac9b6e8d8d5f0a33ba54d7b.jpg")
                .apply(requestOptions04)
                .into(iv_pic04);
    }

    @Override
    protected void initData() {
        @SuppressLint("ResourceAsColor")
        SpannableStringBuilder stringBuilder = SpannableStringUtils
                .getBuilder("五指山")
                .append("\n白茫茫大地真干净").setForegroundColor(getResources().getColor(R.color.background_info))
                .setClickSpan(new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        mPresent.requestData();
                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {

                    }
                })
                .create();
        stringBuilder = SpannableStringUtils.getBuilder(stringBuilder)
                .append("\n,拔剑四顾心茫然").setBoldItalic().setForegroundColor(getResources().getColor(R.color.colorPrimary))
                .setClickSpan(new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {

                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {

                    }
                })
                .create();
        stringBuilder = SpannableStringUtils.getBuilder(stringBuilder)
                .append("\n,黑云压城城欲摧").setForegroundColor(getResources().getColor(R.color.background_error)).setBlur(3,BlurMaskFilter.Blur.NORMAL)
                .setClickSpan(new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {

                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {

                    }
                })
                .create();
        tv_message.setText(stringBuilder);
        tv_message.setMovementMethod(LinkMovementMethod.getInstance());
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
        tv_message.setText("加载数据失败");

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
        tv_net_text.setText(contentBeanList.toString());
    }


}
