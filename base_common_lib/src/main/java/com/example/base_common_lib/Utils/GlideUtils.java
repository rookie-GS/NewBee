package com.example.base_common_lib.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.base_common_lib.Constant;
import com.example.base_common_lib.R;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * 作者：G_JS
 * 时间：
 */
public class GlideUtils {
    public static int BG_DEF = R.color.lightgrey;
    public static Context mContext = Other_Utils.getContext();

    //加载普通图片start
    @SuppressLint("CheckResult")
    public static void load_image(ImageView iv,String url){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(BG_DEF);
        requestOptions.placeholder(BG_DEF);

        Glide.with(mContext)
                .load(url)
                .apply(requestOptions)
                .into(iv);
    }
    @SuppressLint("CheckResult")
    public static void load_image(ImageView iv,int url){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(BG_DEF);
        requestOptions.placeholder(BG_DEF);

        Glide.with(mContext)
                .load(url)
                .apply(requestOptions)
                .into(iv);
    }
    //加载普通图片end


    //加载圆形图片start
    @SuppressLint("CheckResult")
    public static void load_circle_image(ImageView iv,String url){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(BG_DEF);
        requestOptions.placeholder(BG_DEF);
        requestOptions.transform(new CircleCrop());
        Glide.with(mContext)
                .load(url)
                .apply(requestOptions)
                .into(iv);
    }
    @SuppressLint("CheckResult")
    public static void load_circle_image(ImageView iv,int url){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(BG_DEF);
        requestOptions.placeholder(BG_DEF);
        requestOptions.transform(new CircleCrop());
        Glide.with(mContext)
                .load(url)
                .apply(requestOptions)
                .into(iv);
    }
    //加载圆形图片end


    //加载圆角图片start
    @SuppressLint("CheckResult")
    public static void load_radius_corner_image(ImageView iv,String url,int radius){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(BG_DEF);
        requestOptions.placeholder(BG_DEF);
        requestOptions.transform(new RoundedCorners(radius));
        Glide.with(mContext)
                .load(url)
                .apply(requestOptions)
                .into(iv);
    }
    @SuppressLint("CheckResult")
    public static void load_radius_corner_image(ImageView iv,int url,int radius){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(BG_DEF);
        requestOptions.placeholder(BG_DEF);
        requestOptions.transform(new RoundedCorners(radius));
        Glide.with(mContext)
                .load(url)
                .apply(requestOptions)
                .into(iv);
    }
    //加载圆角图片end


    //加载高斯模糊图片start
    @SuppressLint("CheckResult")
    public static void load_blur_image(ImageView iv, String url, int radius){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(BG_DEF);
        requestOptions.placeholder(BG_DEF);
        requestOptions.transform(new BlurTransformation(radius, 2));
        Glide.with(mContext)
                .load(url)
                .apply(requestOptions)
                .into(iv);
    }
    @SuppressLint("CheckResult")
    public static void load_blur_image(ImageView iv,int url,int radius){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(BG_DEF);
        requestOptions.placeholder(BG_DEF);
        requestOptions.transform(new BlurTransformation(radius, 2));
        Glide.with(mContext)
                .load(url)
                .apply(requestOptions)
                .into(iv);
    }
    //加载高斯模糊图片end

    //加载过度动画时间图片start
    @SuppressLint("CheckResult")
    public static void load_dration_image(ImageView iv,String url ,int time){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(BG_DEF);
        requestOptions.placeholder(BG_DEF);
        Glide.with(mContext)
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade(time)) // 渐变
                .into(iv);
    }

    @SuppressLint("CheckResult")
    public static void load_dration_image(ImageView iv,int url ,int time){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(BG_DEF);
        requestOptions.placeholder(BG_DEF);
        Glide.with(mContext)
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade(time)) // 渐变
                .into(iv);
    }
    //加载过度动画时间图片

}
