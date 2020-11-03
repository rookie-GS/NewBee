package com.example.testlib.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.base_common_lib.Utils.GlideUtils;
import com.example.base_common_lib.bean.Demo_list_bean;
import com.example.testlib.R;
import com.example.testlib.bean.ImageRecyclerBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：G_JS
 * 时间：
 */
public class ImageRecyclerAdapter extends BaseQuickAdapter<ImageRecyclerBean, BaseViewHolder> {
    public ImageRecyclerAdapter(ArrayList<ImageRecyclerBean> beans) {
        super(R.layout.item_iamge_list,beans);
    }
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ImageRecyclerBean imageRecyclerBean) {
        GlideUtils.load_image(baseViewHolder.getView(R.id.iv_image),imageRecyclerBean.getImage());
    }
}
