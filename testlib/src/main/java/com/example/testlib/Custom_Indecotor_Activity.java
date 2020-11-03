package com.example.testlib;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
import com.example.base_common_lib.Constant;
import com.example.base_common_lib.Utils.GlideUtils;
import com.example.testlib.adapter.Indecotor_PagerAdapter;

import java.util.ArrayList;
import java.util.List;

@Route( path = Arouter_path.TEST_CUSTOM_INDECOTOR_PAGE )
public class Custom_Indecotor_Activity extends BaseTitleActivity {
    ViewPager vp;
    Indecotor_PagerAdapter mVpAdapter;
    private List<WebView> mList;
    LinearLayout ll_indecotor;
    @Override
    protected String setTextTitle() {
        return "自定义指示器页面";
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
    protected int getContentView() {
        return R.layout.activity_custom__indecotor;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ll_indecotor = findViewById(R.id.ll_indecotor);
        vp = findViewById(R.id.vp);
        //给ViewPager添加图片显示
        mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
//            ImageView imageView = new ImageView(this);
//            if(i ==0)
//                GlideUtils.load_image(imageView, Constant.luka_image_url);
//            if(i == 1)
//                GlideUtils.load_image(imageView,Constant.ai_image_url);
//            if(i == 2)
//                GlideUtils.load_image(imageView, Constant.kobe_image_url);
//            if(i == 3)
//                GlideUtils.load_image(imageView, Constant.kobe_image_url_second);
//            if(i == 4)
//                GlideUtils.load_image(imageView, Constant.nash_image_url);
            WebView webView = new WebView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400);
            webView.setLayoutParams(params);
            webView.setBackgroundColor(Color.GREEN);
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://sdk.zaoyx.com/api/sdk/user/index?data=5lsnFyQ%2BRVo%2BGCh%2FZVEyEgUTXzkDURFfSURfDxtudmk3YThdLT5uPGQxH1cOWGAIGAUnQVciVGFjYCVvQ1AWQQEuAjc3Gh02WjcOenxfFCFjEjpAalgsPy17ZmEMNTBcXFcxDlUXWxN2Rz4GODcpBShBLiAPCldRMxA8b2cMGmJOGQgoT3A%2FT1EQXQNTOSEsAlRlJ3gWZ30lYE9TZlwua2UaUw%3D%3D&key=nRWqsy4TFBzp7pGJyVJy2rTUXKpPnh4jI55LOukSYA17PY19oH5s7GM3p%2Fo%2BQPZPKfdbX0AGHV4fztt8ooODzvUM1ZM5jBszGK%2FuafP7ipEvYdMng8k%2F6oggnTDwFU6OP2ol93ubwZ%2Fv%2BWSCN3fCh55Mj4DnvlTJfqXNOx%2FBam8%3D");
            mList.add(webView);
        }
        mVpAdapter = new Indecotor_PagerAdapter(mList);
        vp.setAdapter(mVpAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i <5 ; i++) {
                    ImageView iv = (ImageView) ll_indecotor.getChildAt(i);
                    // 当前滑到的是那一页就让第几个小圆点处于选中状态
                    if (position == i) {
                        iv.setEnabled(true);
                    } else {
                        iv.setEnabled(false);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initPoint();
    }

    @Override
    protected void initData() {

    }

    /**
     * 初始化指示器
     */
    private void initPoint() {
        for (int i = 0; i < mList.size(); i++) {
            // 往llContainer添加一个小圆点
            ImageView iv = new ImageView(this);
            //设置背景
            iv.setImageResource(R.drawable.indecotor_pick);
            //设置原点大小
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(40, 40);
            //如果是切好的图片就直接自适应
//      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            iv.setLayoutParams(params);
            // 默认第0个小圆点显示红色
            if (i == 0) {
                iv.setEnabled(true);
            } else {
                iv.setEnabled(false);
            }
            //设置原点之间的间距
            iv.setPadding(10, 10, 10, 10);
            //添加到LinearLayout中
            ll_indecotor.addView(iv);
        }
    }
}