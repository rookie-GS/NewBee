package com.example.testlib;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
import com.example.base_common_lib.Constant;
import com.example.base_common_lib.Utils.GlideUtils;

import jp.wasabeef.glide.transformations.BlurTransformation;

@Route( path = Arouter_path.TEST_IMAGE_PAGE)
public class GlideActivity extends BaseTitleActivity {
    ImageView iv_pic_one,iv_pic_two,iv_pic_three,iv_pic_four,iv_pic_five;
    @Override
    protected boolean setToolbvis() {
        return true;
    }

    @Override
    protected String setTextTitle() {
        return "图片库测试";
    }
    @Override
    protected int setTitlecolor() {
        return R.color.black;
    }
    @Override
    protected int setToolbgcolor() {
        return R.color.white;
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_glide;
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        iv_pic_one = findViewById(R.id.iv_pic_one);
        iv_pic_two = findViewById(R.id.iv_pic_two);
        iv_pic_three = findViewById(R.id.iv_pic_three);
        iv_pic_four = findViewById(R.id.iv_pic_four);
        iv_pic_five = findViewById(R.id.iv_pic_five);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        GlideUtils.load_image(iv_pic_one,Constant.kobe_image_url);
        GlideUtils.load_dration_image(iv_pic_two,Constant.kobe_image_url,3000);
        GlideUtils.load_circle_image(iv_pic_three,Constant.kobe_image_url);
        GlideUtils.load_radius_corner_image(iv_pic_four,Constant.kobe_image_url,50);
        GlideUtils.load_blur_image(iv_pic_five,Constant.kobe_image_url,20);
    }
}