package com.example.testlib;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
import com.example.base_common_lib.Image_Url;
import com.example.base_common_lib.Utils.DimenUtil;
import com.example.base_common_lib.Utils.GlideUtils;
import com.example.base_common_lib.Utils.ToastUtils;
import com.example.base_common_lib.ui.custom_view.CustomCircleProgressBar;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.ihsg.demo.util.PatternHelper;
import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternLockerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Route( path = Arouter_path.TEST_CIRCLE_BOTTOM_PAGE)
public class Circle_bottom_Activity extends BaseTitleActivity {
    private static final String TAG = "Circle_bottom_Activity";
    CustomCircleProgressBar progressBar;
    boolean continue_flag = true;
    boolean is_full_flag = false;
    PhotoView iv_pic;
    View view_fake;
    TextView tv_dirty,tv_clear;
    int Animation_time = 2000;
    private AlphaAnimation alphaAnimation;
    ArrayList<String> imagelist_clear = new ArrayList<>();
    ArrayList<Integer> imagelist_dirty = new ArrayList<>();
    boolean image_model = true,lock_vis = false;
    int image_index = 0;
    PatternHelper patternHelper;
    PatternLockerView patternLockerView;
    RelativeLayout rl_lock_view;
    LinearLayout ll_controller;
    boolean controller_vis = true;
    int distens = 0;
    @Override
    protected String setTextTitle() {
        return "圆形进度条页面";
    }
    @Override
    protected int setTitlecolor() {
        return R.color.black;
    }
    @Override
    protected boolean setToolbvis() {
        return false;
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_circle_bottom;
    }

    @SuppressLint({"ClickableViewAccessibility", "Range"})
    @Override
    protected void init(Bundle savedInstanceState) {
        setIU();
        setAnimation();
    }
    @Override
    protected void initData() {

        imagelist_clear.add(Image_Url.Kobe_image_url_04);
        imagelist_clear.add(Image_Url.Nash_image_url);
        imagelist_clear.add(Image_Url.AI_image_url_02);
        imagelist_clear.add(Image_Url.Luka_image_url_02);
//        imagelist_clear.add(Image_Url.cjk_image_url);

        imagelist_clear.add(Image_Url.Melo_image_url);
        imagelist_clear.add(Image_Url.Harden_image_url);
        imagelist_clear.add(Image_Url.D_rose_image_url);
        imagelist_clear.add(Image_Url.Kd_image_url);
//        imagelist_clear.add(Image_Url.cjk_image_url_02);


        imagelist_clear.add(Image_Url.Curry_image_url);
        imagelist_clear.add(Image_Url.LBJ_image_url);
        imagelist_clear.add(Image_Url.DW_image_url);
        imagelist_clear.add(Image_Url.cxk_image_url);
//        imagelist_clear.add(Image_Url.cjk_image_url_03);



        imagelist_dirty.add(R.mipmap.sexy_01);
        imagelist_dirty.add(R.mipmap.sexy_02);
        imagelist_dirty.add(R.mipmap.sexy_03);
        imagelist_dirty.add(R.mipmap.sexy_04);
        imagelist_dirty.add(R.mipmap.sexy_05);
        imagelist_dirty.add(R.mipmap.sexy_06);
        imagelist_dirty.add(R.mipmap.sexy_07);
        imagelist_dirty.add(R.mipmap.sexy_08);
        imagelist_dirty.add(R.mipmap.sexy_09);
        imagelist_dirty.add(R.mipmap.sexy_10);
        imagelist_dirty.add(R.mipmap.sexy_11);
        imagelist_dirty.add(R.mipmap.sexy_12);
        imagelist_dirty.add(R.mipmap.sexy_13);

        distens = DimenUtil.dp2px(this,150);

    }
    private void setIU(){
        ll_controller = findViewById(R.id.ll_controller);
        rl_lock_view = findViewById(R.id.rl_lock_view);
        patternLockerView = findViewById(R.id.patternLockerView);
        patternHelper = new PatternHelper();
        tv_dirty = findViewById(R.id.tv_dirty);
        tv_clear = findViewById(R.id.tv_clear);
        view_fake = findViewById(R.id.view_fake);
        view_fake.setVisibility(View.VISIBLE);
        iv_pic = findViewById(R.id.iv_pic);
        iv_pic.setOnPhotoTapListener((view, x, y) -> {
            if(controller_vis){
                hideAnimatorSet(ll_controller);
            }else {
                showAnimatorSet(ll_controller);

            }
            controller_vis = !controller_vis;
        });
        progressBar = findViewById(R.id.ccp_bt);


        setting_locking();

        tv_clear.setOnClickListener(v -> {
            Log.e(TAG, "what_model: clear" );

            if(!image_model){
                rl_lock_view.setVisibility(View.GONE);
                lock_vis = false;
                image_model = true;


                reset();
                image_index = 0;
                is_full_flag = false;
                ToastUtils.showShortToast("非主流模式关闭");

            }

        });
        tv_dirty.setOnClickListener(v -> {
            Log.e(TAG, "what_model: dirty" );
            if(image_model){
                rl_lock_view.setVisibility(View.VISIBLE);
                lock_vis = true;
            }
        });
    }
    @SuppressLint("ClickableViewAccessibility")
    private void setAnimation(){
        progressBar.setRound_time(Animation_time);
        //第一个参数开始的透明度，第二个参数结束的透明度
        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(Animation_time);//多长时间完成这个动作
        progressBar.setIprogress_lisener(new CustomCircleProgressBar.IProgress_status_lisener() {
            @Override
            public void progress_start() {
                Log.e(TAG, "progress_start: ");
            }

            @Override
            public void progress_going(int progress) {

            }

            @Override
            public void progress_stop() {
                Log.e(TAG, "progress_stop: ");
                alphaAnimation.cancel();
            }

            @Override
            public void progress_finsh() {
                Log.e(TAG, "progress_finsh: ");
                is_full_flag = true;
                continue_flag = true;
            }
        });

        progressBar.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if(continue_flag){
                        //震动服务
                        view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                        if(!is_full_flag){
                            continue_flag = false;
                            progressBar.start();
                            if(image_model){
                                Log.e(TAG, "setAnimation: 纯净模式" );
                                GlideUtils.load_image(iv_pic, imagelist_clear.get(image_index));
                            }else {
                                Log.e(TAG, "setAnimation: 非主流模式" );
                                GlideUtils.load_image(iv_pic, imagelist_dirty.get(image_index));
                            }
                            view_fake.setVisibility(View.GONE);
                            iv_pic.startAnimation(alphaAnimation);
                        }else {
                            is_full_flag = false;
                        }

                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    //移动
                    break;
                case MotionEvent.ACTION_UP:
                    if(!is_full_flag){
                        reset();
                    }
                    break;
            }
            return true;
        });
    }

    private void reset(){
        progressBar.reset();
        continue_flag = true;
        iv_pic.clearAnimation();
        view_fake.setVisibility(View.VISIBLE);
        image_index = image_index+1;
        if(image_model){
            if(image_index>= imagelist_clear.size()){
                image_index = 0;
            }
        }else {
            if(image_index>= imagelist_dirty.size()){
                image_index = 0;
            }
        }
    }
    public void hideAnimatorSet(View v) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "translationY", 0f, distens);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(100);
        animatorSet.play(animator1).with(animator2);
        animatorSet.start();
    }
    public void showAnimatorSet(View v) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "translationY", distens, 0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(100);
        animatorSet.play(animator1).with(animator2);
        animatorSet.start();
    }

    private void setting_locking(){
        patternLockerView.setOnPatternChangedListener(new OnPatternChangeListener() {
            @Override
            public void onStart(@NotNull PatternLockerView patternLockerView) {
                Log.e(TAG, "locking___onStart: " );
            }

            @Override
            public void onChange(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {
                Log.e(TAG, "locking___onChange: " );
            }

            @Override
            public void onComplete(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {

                Log.e(TAG, "locking___onComplete: " );

                patternHelper.validateForChecking(list);
                boolean isOk =patternHelper.isOk();
                patternLockerView.updateStatus(isOk);
                ToastUtils.showShortToast(patternHelper.getMessage());
                patternLockerView.clearHitState();
                if(isOk){
                    image_model = false;
                    reset();
                    image_index = 0;
                    is_full_flag = false;
                    ToastUtils.showShortToast("非主流模式开启");

                }
                rl_lock_view.setVisibility(View.GONE);
                lock_vis = false;
            }

            @Override
            public void onClear(@NotNull PatternLockerView patternLockerView) {
                Log.e(TAG, "locking___onClear: " );
            }
        });
    }

    @Override
    //安卓重写返回键事件
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(lock_vis){
                rl_lock_view.setVisibility(View.GONE);
                lock_vis = false;
            }else {
                finish();
            }
        }
        return true;
    }
}