package com.example.testlib;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BlurMaskFilter;
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
    protected int setToolbgcolor() {
        return R.color.blue;
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

    @Override
    protected void initData() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.color.black);
        requestOptions.placeholder(R.color.black);

        Glide.with(this)
                .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=245785532,241083071&fm=26&gp=0.jpg")
                .apply(requestOptions)
                .into(iv_pic_one);
        RequestOptions requestOptions02 = new RequestOptions();
        requestOptions02.placeholder(R.color.black);
        requestOptions02.placeholder(R.color.black);
        Glide.with(this)
                .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=245785532,241083071&fm=26&gp=0.jpg")
                .apply(requestOptions02)
                .transition(DrawableTransitionOptions.withCrossFade(3000)) // 渐变
                .into(iv_pic_two);
        RequestOptions requestOptions03 = new RequestOptions();
        requestOptions03.placeholder(R.color.black);
        requestOptions03.placeholder(R.color.black);
        requestOptions03.transform(new RoundedCorners(50));
        Glide.with(this)
                .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=245785532,241083071&fm=26&gp=0.jpg")
                .apply(requestOptions03)
                .into(iv_pic_three);
        RequestOptions requestOptions04 = new RequestOptions();
        requestOptions04.placeholder(R.color.black);
        requestOptions04.placeholder(R.color.black);
        requestOptions04.transform(new CircleCrop());
        Glide.with(this)
                .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=245785532,241083071&fm=26&gp=0.jpg")
                .apply(requestOptions04)
                .into(iv_pic_four);
        RequestOptions requestOptions05 = new RequestOptions();
        requestOptions05.placeholder(R.color.black);
        requestOptions05.placeholder(R.color.black);
        requestOptions05.transform(new BlurTransformation(20, 2));
        Glide.with(this)
                .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=245785532,241083071&fm=26&gp=0.jpg")
                .apply(requestOptions05)
                .into(iv_pic_five);
    }
}