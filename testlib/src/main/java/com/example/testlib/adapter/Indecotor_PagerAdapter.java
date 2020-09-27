package com.example.testlib.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * 作者：G_JS
 * 时间：
 */
public class Indecotor_PagerAdapter extends androidx.viewpager.widget.PagerAdapter {
    private List<WebView> list;

    public Indecotor_PagerAdapter(List<WebView> list) {
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(list.get(position));//添加页卡
        return list.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(list.get(position));//删除页卡
    }
}
