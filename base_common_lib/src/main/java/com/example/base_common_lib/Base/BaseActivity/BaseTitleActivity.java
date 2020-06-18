package com.example.base_common_lib.Base.BaseActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.ViewStub;

import com.example.base_common_lib.R;

public abstract class BaseTitleActivity extends SuperInitActivity {

    @Override
    public void setContentViews(Bundle savedInstanceState) {
        super.setContentViews(savedInstanceState);
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
        //默认每个页面都显示左上角关闭页面图片
        setReplaceTopLeftButton(R.mipmap.my_back_b, null);
    }

    @Override
    protected void initOther() {
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.v3_app_new));
    }

    public int getTitleHight() {
        int height = toolbar.getHeight();
        Rect rectangle = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        return height + rectangle.top;

    }

}
