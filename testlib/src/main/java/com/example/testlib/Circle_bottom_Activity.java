package com.example.testlib;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.cardview.widget.CardView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.didichuxing.doraemonkit.DoraemonKit;
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
    ImageView view_fake,iv_simple_show,iv_simple_bottom;

    int Animation_time = 2000;
    ArrayList<String> imagelist_clear = new ArrayList<>();
    ArrayList<Object> imagelist_dirty = new ArrayList<>();
    boolean image_model = true;
    int image_index = 0;
    PatternHelper patternHelper;
    PatternLockerView patternLockerView;
    RelativeLayout rl_lock_view;
    LinearLayout ll_controller;
    int distens = 0;
    private AnimatorSet animatorFake_hide;
    private AnimatorSet animatorFake_show;
    int blur_num = 100;

    CardView cv_clear_model,cv_dirty_model;
    LinearLayout ll_intro;
    private AnimatorSet animatorSetBt_hide;
    private AnimatorSet animatorSetBt_show;

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
        setView_fake();
        setAnimation();
    }
    @Override
    protected void initData() {

        imagelist_clear.add(Image_Url.Kobe_image_url_04);
        imagelist_clear.add(Image_Url.Nash_image_url);
        imagelist_clear.add(Image_Url.AI_image_url_02);
        imagelist_clear.add(Image_Url.Luka_image_url_02);
        imagelist_clear.add(Image_Url.Melo_image_url);
        imagelist_clear.add(Image_Url.Harden_image_url);
        imagelist_clear.add(Image_Url.D_rose_image_url);
        imagelist_clear.add(Image_Url.Kd_image_url);
        imagelist_clear.add(Image_Url.Curry_image_url);
        imagelist_clear.add(Image_Url.LBJ_image_url);
        imagelist_clear.add(Image_Url.DW_image_url);
        imagelist_clear.add(Image_Url.ai_image_url);
        imagelist_clear.add(Image_Url.Kobe_image_url_03);
        imagelist_clear.add(Image_Url.nba_10);
        imagelist_clear.add(Image_Url.nash_image_url);
        imagelist_clear.add(Image_Url.nba_01);
        imagelist_clear.add(Image_Url.nba_02);
        imagelist_clear.add(Image_Url.nba_03);
        imagelist_clear.add(Image_Url.nba_04);
        imagelist_clear.add(Image_Url.nba_05);
        imagelist_clear.add(Image_Url.nba_06);
        imagelist_clear.add(Image_Url.nba_07);
        imagelist_clear.add(Image_Url.nba_08);
        imagelist_clear.add(Image_Url.nba_09);
        imagelist_clear.add(Image_Url.cxk_image_url);



        imagelist_dirty.add(Image_Url.cjk_image_url);
        imagelist_dirty.add(Image_Url.bdyjy_image_url);
        imagelist_dirty.add(Image_Url.cjk_image_url_02);
        imagelist_dirty.add(Image_Url.sexy_01);
        imagelist_dirty.add(Image_Url.sexy_02);
        imagelist_dirty.add(Image_Url.sexy_03);
        imagelist_dirty.add(Image_Url.sexy_04);
        imagelist_dirty.add(Image_Url.sexy_05);
        imagelist_dirty.add(Image_Url.sexy_06);
        imagelist_dirty.add(Image_Url.sexy_07);
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


        init_fake_view();

    }
    private void setIU(){
        /**设置模式选择界面*/
        ll_intro = findViewById(R.id.ll_intro);
        ll_intro.setVisibility(View.VISIBLE);
        cv_clear_model = findViewById(R.id.cv_clear_model);
        cv_dirty_model = findViewById(R.id.cv_dirty_model);
        cv_clear_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_intro.setVisibility(View.GONE);
            }
        });
        cv_dirty_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_intro.setVisibility(View.GONE);
                rl_lock_view.setVisibility(View.VISIBLE);

                image_model = false;
                init_fake_view();
                image_index = 0;
                is_full_flag = false;
            }
        });
        /**设置锁界面*/
        rl_lock_view = findViewById(R.id.rl_lock_view);
        patternLockerView = findViewById(R.id.patternLockerView);
        patternHelper = new PatternHelper();
        setting_locking();


        /**设置图片主界面*/
        iv_simple_bottom = findViewById(R.id.iv_simple_bottom);
        iv_simple_show = findViewById(R.id.iv_simple_show);
        iv_simple_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_pic.setRotationTo(0);
                iv_simple_show.setVisibility(View.GONE);
                animatorSetBt_hide.start();

            }
        });
        view_fake = findViewById(R.id.view_fake);
        view_fake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLongToast("长按解锁观看");
            }
        });
        iv_pic = findViewById(R.id.iv_pic);
        iv_pic.setOnPhotoTapListener((view, x, y) -> {
                iv_simple_show.setVisibility(View.VISIBLE);
                animatorSetBt_show.start();
        });
        /**设置刷新按钮*/
        ll_controller = findViewById(R.id.ll_controller);
        progressBar = findViewById(R.id.ccp_bt);
        bt_hide_show_AnimatorSet();


    }

    /**设置长按动画*/
    @SuppressLint("ClickableViewAccessibility")
    private void setAnimation(){
        progressBar.setRound_time(Animation_time);
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
                animatorFake_hide.cancel();
                animatorFake_show.start();
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
                            int bottom_index = image_index+1;
                            if(image_model){
                                if(bottom_index>=imagelist_clear.size()){
                                    bottom_index = 0;
                                }
                                GlideUtils.load_blur_image(iv_simple_bottom, imagelist_clear.get(bottom_index),blur_num);

                            }else {
                                if(bottom_index>=imagelist_dirty.size()){
                                    bottom_index = 0;
                                }
                                GlideUtils.load_blur_image(iv_simple_bottom, imagelist_dirty.get(bottom_index),blur_num);

                            }
                            progressBar.start();
                            animatorFake_hide.start();
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
    /**图片重置*/
    private void reset(){
        progressBar.reset();
        continue_flag = true;
        animatorFake_hide.cancel();
        animatorFake_show.start();
        is_full_flag = false;
        image_index = image_index+1;
        if(image_model){
            if(image_index>= imagelist_clear.size()){
                image_index = 0;
            }
            Log.e(TAG, "setAnimation: 纯净模式" );
            GlideUtils.load_blur_image(view_fake, imagelist_clear.get(image_index),blur_num);
            GlideUtils.load_image(iv_simple_show, imagelist_clear.get(image_index));
            GlideUtils.load_image(iv_pic, imagelist_clear.get(image_index));

        }else {
            if(image_index>= imagelist_dirty.size()){
                image_index = 0;
            }
            Log.e(TAG, "setAnimation: 非主流模式" );
            GlideUtils.load_blur_image(view_fake, imagelist_dirty.get(image_index),blur_num);
            GlideUtils.load_image(iv_simple_show, imagelist_dirty.get(image_index));
            GlideUtils.load_image(iv_pic, imagelist_dirty.get(image_index));

        }
    }
    /**图片初始化*/
    private void init_fake_view(){
        progressBar.reset();
        continue_flag = true;
        animatorFake_show.start();
        image_index = 0;
        is_full_flag = false;
        if(image_model){
            Log.e(TAG, "setAnimation: 纯净模式" );

            GlideUtils.load_blur_image(view_fake, imagelist_clear.get(image_index),blur_num);
            GlideUtils.load_image(iv_simple_show, imagelist_clear.get(0));
            GlideUtils.load_image(iv_pic, imagelist_clear.get(0));
            GlideUtils.load_blur_image(iv_simple_bottom, imagelist_clear.get(1),blur_num);


        }else {
            Log.e(TAG, "setAnimation: 非主流模式" );
            GlideUtils.load_blur_image(view_fake, imagelist_dirty.get(image_index),blur_num);
            GlideUtils.load_image(iv_simple_show, imagelist_dirty.get(0));
            GlideUtils.load_image(iv_pic, imagelist_dirty.get(0));
            GlideUtils.load_blur_image(iv_simple_bottom, imagelist_dirty.get(1),blur_num);


        }
    }
    /**控制按钮的隐藏与可见*/
    public void bt_hide_show_AnimatorSet() {
        distens = DimenUtil.dp2px(this,150);
        ObjectAnimator animator_hide_01 = ObjectAnimator.ofFloat(ll_controller, "translationY", 0f, distens);
        ObjectAnimator animator_hide_02 = ObjectAnimator.ofFloat(ll_controller, "alpha", 1f, 0f);
        animatorSetBt_hide = new AnimatorSet();
        animatorSetBt_hide.setDuration(100);
        animatorSetBt_hide.play(animator_hide_01).with(animator_hide_02);
        ObjectAnimator animator_show_01 = ObjectAnimator.ofFloat(ll_controller, "translationY", distens, 0f);
        ObjectAnimator animator_show_02 = ObjectAnimator.ofFloat(ll_controller, "alpha", 0f, 1f);
        animatorSetBt_show = new AnimatorSet();
        animatorSetBt_show.setDuration(100);
        animatorSetBt_show.play(animator_show_01).with(animator_show_02);
    }


    public void setView_fake() {
        ObjectAnimator animator_hide = ObjectAnimator.ofFloat(view_fake, "alpha", 1f, 0f);
        animatorFake_hide = new AnimatorSet();
        animatorFake_hide.setDuration(Animation_time);
        animatorFake_hide.play(animator_hide);
        animatorFake_hide.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view_fake.setVisibility(View.GONE);
            }
        });


        ObjectAnimator animator_show = ObjectAnimator.ofFloat(view_fake, "alpha", 0f, 1f);
        animatorFake_show = new AnimatorSet();
        animatorFake_show.setDuration(1);
        animatorFake_show.play(animator_show);
        animatorFake_show.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view_fake.setVisibility(View.VISIBLE);
            }
        });
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
                patternLockerView.clearHitState();
                if(isOk){
                    ToastUtils.showShortToast("非主流模式开启");
                    rl_lock_view.setVisibility(View.GONE);
                }else {
                    if(patternHelper.getremainTimes()==0){
                        finish();
                    }else {
                        ToastUtils.showShortToast(patternHelper.getMessage());
                    }
                }

            }

            @Override
            public void onClear(@NotNull PatternLockerView patternLockerView) {
                Log.e(TAG, "locking___onClear: " );
            }
        });
    }

}