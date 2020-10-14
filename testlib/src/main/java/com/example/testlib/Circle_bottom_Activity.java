package com.example.testlib;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
import com.example.base_common_lib.Utils.GlideUtils;
import com.example.base_common_lib.ui.custom_view.CustomCircleProgressBar;

import java.util.ArrayList;

@Route( path = Arouter_path.TEST_CIRCLE_BOTTOM_PAGE)
public class Circle_bottom_Activity extends BaseTitleActivity {
    private static final String TAG = "Circle_bottom_Activity";
    CustomCircleProgressBar progressBar;
    boolean continue_flag = true;
    boolean is_full_flag = false;
    ImageView iv_pic;
    View view_fake;
    int Animation_time = 2000;
    private AlphaAnimation alphaAnimation;
    ArrayList<Integer> imagelist = new ArrayList<>();
    int image_index = 0;

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
        return true;
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_circle_bottom;
    }

    @SuppressLint({"ClickableViewAccessibility", "Range"})
    @Override
    protected void init(Bundle savedInstanceState) {
        view_fake = findViewById(R.id.view_fake);
        view_fake.setVisibility(View.VISIBLE);
        iv_pic = findViewById(R.id.iv_pic);
        imagelist.add(R.mipmap.sexy_01);
        imagelist.add(R.mipmap.sexy_02);
        imagelist.add(R.mipmap.sexy_03);
        imagelist.add(R.mipmap.sexy_04);
        imagelist.add(R.mipmap.sexy_05);
        imagelist.add(R.mipmap.sexy_06);
        imagelist.add(R.mipmap.sexy_07);
        imagelist.add(R.mipmap.sexy_08);
        imagelist.add(R.mipmap.sexy_09);
        imagelist.add(R.mipmap.sexy_10);
        imagelist.add(R.mipmap.sexy_11);
        imagelist.add(R.mipmap.sexy_12);
        imagelist.add(R.mipmap.sexy_13);

        progressBar = findViewById(R.id.ccp_bt);
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
                            Log.e(TAG, "tupian: "+imagelist.get(image_index) );
                            GlideUtils.load_image(iv_pic, imagelist.get(image_index));
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
                            progressBar.reset();
                            continue_flag = true;
                            iv_pic.clearAnimation();
                            view_fake.setVisibility(View.VISIBLE);
                            image_index = image_index+1;
                            if(image_index>=imagelist.size()){
                                image_index = 0;
                            }
                        }
                    break;
            }
            return true;
        });

    }

    @Override
    protected void initData() {

    }
}