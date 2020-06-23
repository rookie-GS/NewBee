package com.example.base_common_lib.Base.BaseActivity;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.base_common_lib.R;

/**
 * @Author G_JS
 *
 * @Date Created by 2020/6/11 10:37 
 * 
 */
public class SuperTopBarActivity extends SuperTopbarBaseActivity {
    private OnClickListener onClickListenerTopLeft;
    private OnClickListener onClickListenerTopRight;
    private int menuResId;
    private String menuStr;
    public MenuItem menuRightItem;
    /**
     * 设置中间title
     *
     * @param title title
     */
    protected void setTitleText(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
    }

    protected void set_right_text(String text){
        if(!TextUtils.isEmpty(text)){
            tv_right_text.setText(text);
        }
    }
    protected void set_right_text_click(View.OnClickListener listener){
        tv_right_text.setOnClickListener(listener);
    }

    /**
     * 设置中间title
     * @param title title
     */
    protected void setTitleText(int title){
        if (title != 0) {
            tvTitle.setText(title);
        }
    }
    /**
     * 设置中间title
     * @param
     */
    protected void setTitle_color(int color){

        tvTitle.setTextColor(getResources().getColor(color));
        tv_right_text.setTextColor(getResources().getColor(color));
    }

    public void setToolbar_bg_color(int color){
        toolbar.setBackgroundColor(getResources().getColor(color));
    }
    public void settoorbar_vis(boolean vis){
        if(vis){
            toolbar.setVisibility(View.VISIBLE);
        }else {
            toolbar.setVisibility(View.GONE);
        }
    }
    /**
     * 可以更换左上角默认显示图片
     *
     * @param iconResId 资源id
     */
    public void setReplaceTopLeftButton(int iconResId) {
        setTopLeftButton(iconResId, null);
    }

    /**
     * 可以更换左上角默认显示图片
     *
     * @param iconResId       资源id
     * @param onClickListener 点击事件监听
     */
    public void setReplaceTopLeftButton(int iconResId, OnClickListener onClickListener) {
        setTopLeftButton(iconResId, onClickListener);
    }

    @SuppressLint("NewApi")
    protected void setTopLeftButton(int iconResId, OnClickListener onClickListener) {
        toolbar.setNavigationIcon(iconResId);

        this.onClickListenerTopLeft = onClickListener;
    }

    protected void setTopLeftButton(OnClickListener onClickListener) {
        this.onClickListenerTopLeft = onClickListener;
    }

    /**
     * title右边内容
     *
     * @param menuStr         内容
     * @param onClickListener 点击监听
     */
    public void setTopRightButton(String menuStr, OnClickListener onClickListener) {
        this.onClickListenerTopRight = onClickListener;
        this.menuStr = menuStr;
    }

    /***
     * 更改右边内容
     * @param menuStr 内容

     */
    protected void setTopRightButton(String menuStr) {
        this.menuStr = menuStr;
    }

    /**
     * title右边内容
     *
     * @param menuResId       资源id
     * @param onClickListener 事件点击
     */
    protected void setTopRightButton(int menuResId, OnClickListener onClickListener) {
        this.menuResId = menuResId;
        this.onClickListenerTopRight = onClickListener;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuResId != 0 || !TextUtils.isEmpty(menuStr)) {
            getMenuInflater().inflate(R.menu.menu_activity_base_top_bar, menu);
        }
        return true;
    }

    //动态修改内容。后边菜单
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menuResId != 0) {
            menuRightItem = menu.findItem(R.id.menu_1).setIcon(menuResId);
        }
        if (!TextUtils.isEmpty(menuStr)) {
            menuRightItem = menu.findItem(R.id.menu_1).setTitle(menuStr);
        }
        modifyRightMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (onClickListenerTopLeft != null) {
                onClickListenerTopLeft.onClick();
            } else {
                finish();       //不设置onClickListenerTopLeft监听，默认直接finish();
            }
        } else if (item.getItemId() == R.id.menu_1) {
            onClickListenerTopRight.onClick();
        }

        return true; // true 告诉系统我们自己处理了点击事件
    }

    protected void modifyRightMenu(){
    }

    public interface OnClickListener {
        void onClick();
    }
}
