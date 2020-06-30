package com.example.base_common_lib.Base;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.base_common_lib.R;
import com.example.base_common_lib.Utils.ToastUtils;
import com.example.base_common_lib.bean.Demo_list_bean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @Author G_JS
 *
 * @Date Created by 2020/6/30 9:19
 *
 */
public class CommonAdapter extends BaseQuickAdapter<Demo_list_bean, BaseViewHolder> {
    public CommonAdapter(ArrayList<Demo_list_bean> beans) {
        super(R.layout.item_demo_list,beans);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Demo_list_bean demo_list_bean) {
        ((TextView)baseViewHolder.getView(R.id.tv_name)).setText(demo_list_bean.getName());
    }
}
