package com.example.base_common_lib.Base.BaseActivity;

import android.os.Bundle;
import android.view.ViewStub;

import com.example.base_common_lib.Base.BasePresenter;
import com.example.base_common_lib.R;


public abstract class BaseMVPActivity<V, T extends BasePresenter<V>> extends SuperInitActivity {
    public T mPresent;

    @Override
    protected void initMvpPresenter() {
        mPresent = createPresent();
        mPresent.attachView((V) this);
    }

    @Override
    public void setContentViews(Bundle savedInstanceState) {
        super.setContentViews(savedInstanceState);
    }

    public abstract T createPresent();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresent != null) {
            mPresent.detach();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void initViewStub() {
        try {
            ViewStub titleViewStub = findViewById(R.id.title_view_stub);
            titleViewStub.inflate();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initTitleBar() {
        //初始化设置 Toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        //设置title
        setTitleText(setTextTitle());
        setToolbar_bg_color(setToolbgcolor());
        settoorbar_vis(setToolbvis());
        //默认每个页面都显示左上角关闭页面图片
        setReplaceTopLeftButton(R.mipmap.my_back_b, null);
    }

}
