package com.example.base_common_lib.Base.BaseActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;

import com.example.base_common_lib.R;


/**
 * @Author G_JS
 * 公共方法界面跳转 基本操作初始化
 * @Date Created by 2020/6/11 13:49
 * 
 */
public abstract class SuperInitActivity extends SuperOtherActivity {

    @Override
    protected void setContentViews(Bundle savedInstanceState) {
        LayoutInflater.from(this).inflate(getContentView(), viewContent);
        //mvp
        initMvpPresenter();

        //初始化操作
        init(savedInstanceState);
        //网络加载
        initData();

        //初始化其他操作
        initOther();

        //屏幕常亮
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    /**
     * 所有事件完毕，  初始化其他， 重写此方法
     */
    protected void initOther() {

    }

    /**
     * 创建P层对象
     */
    protected void initMvpPresenter() {
    }

    /**
     * 如果需要初始化titleBar重写
     */
    protected void initTitleBar() {

    }

    /**
     * 获取setContentView
     *
     * @return view
     */
    protected abstract int getContentView();

    /**
     * title
     *
     * @return title
     */
    protected String setTextTitle() {
        return "";
    }
    /**
     * title_color
     *
     * @return title
     */
    protected int setToolbgcolor() {
        return R.color.white;
    }
    /**
     * title_vis
     *
     * @return title
     */
    protected boolean setToolbvis() {
        return false;
    }
    /**
     * 初始化
     *
     * @param savedInstanceState 状态保存
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 加载数据
     */
    protected abstract void initData();

    /**
     * 跳转
     *
     * @param activity activity
     */
    public void startNextActivity(Class activity) {
        Intent intent = new Intent(mContext, activity);
        startActivity(intent);
    }

    /**
     * 跳转 传值
     *
     * @param bundle   Bundle
     * @param activity activity
     */
    public void startNextActivity(Bundle bundle, Class activity) {
        Intent intent = new Intent(mContext, activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转返回 回调
     *
     * @param activity    activity
     * @param requestCode 请求码
     */
    public void startNextActivity(Class activity, int requestCode) {
        Intent intent = new Intent(mContext, activity);
        startActivityForResult(intent, requestCode);
    }

    public void startNextActivity(Bundle bundle, Class activity, int requestCode) {
        Intent intent = new Intent(mContext, activity);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    public void startNextActivity(Bundle bundle, String modelUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(modelUrl));
        intent.putExtras(bundle);
        startActivity(intent, bundle);
    }

    public Intent startIntentService(Bundle bundle, Class service) {
        Intent intent = new Intent(mContext, service);
        intent.putExtras(bundle);
        startService(intent);
        return intent;
    }

    public void startNextActivity(String pager, Bundle bundle) {
        Intent intent = new Intent(pager);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 链接启动activity
     *
     * @param uriPath 路径地址
     */
    public void startNextActivity(String uriPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriPath));
        startActivity(intent);
    }

    public String getIntents(String key) {
        return getIntent().getStringExtra(key);
    }

    /**
     * 网络错误页面加载
     */
    public void nextErrPager() {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_next_err_pager, null);
        //事件操作
        againLoadData(view);
    }

    /**
     * 网络错误页面加载自定义布局
     *
     * @param layoutRes 布局
     */
    public void nextErrPager(@LayoutRes int layoutRes) {
        View view = LayoutInflater.from(this).inflate(layoutRes, null);
        againLoadData(view);
    }

    /**
     * 网络错误页面加载自定义布局
     *
     * @param view 布局
     */
    public void nextErrPager(View view) {
        againLoadData(view);
    }

    /**
     * 点击重新获取
     *
     * @param view 布局
     */
    private void againLoadData(final View view) {
        viewContent.addView(view);
        ImageView nextErrPager = view.findViewById(R.id.net_err);
        nextErrPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewContent.removeViewInLayout(view);
                initData();
            }
        });
    }

    /**
     * 动态权限管理sd卡操作
     */
    public void startAlbum(final boolean isCapture, final int maxNum) {



    }


}
