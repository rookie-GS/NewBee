package com.example.base_common_lib.Base;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.base_common_lib.R;
import com.example.base_common_lib.Utils.GlideUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @Author G_JS
 * 
 * @Date Created by 2020/7/1 17:50 
 * 
 */
public class Pic_Adapter extends BaseQuickAdapter<Uri, BaseViewHolder> {
    public Pic_Adapter( @Nullable List<Uri> data) {
        super(R.layout.item_pic_selector, data);
    }
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Uri s) {
        GlideUtils.load_image(baseViewHolder.getView(R.id.iv_pic),s);
    }
}
