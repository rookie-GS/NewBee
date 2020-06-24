package com.example.testlib;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.didichuxing.doraemonkit.kit.viewcheck.LayoutBorderView;
import com.example.base_common_lib.Utils.Other_Utils;
import com.example.base_common_lib.Utils.ToastUtils;
import com.example.base_common_lib.bean.Demo_list_bean;

import java.util.ArrayList;

/**
 * 作者：G_JS
 * 时间：
 */
public class Demo_list_adapter extends RecyclerView.Adapter<Demo_list_adapter.ViewHolder> {
    private ArrayList<Demo_list_bean> mList = new ArrayList<>();
    private Context mContext = Other_Utils.getContext();
    public Demo_list_adapter(ArrayList<Demo_list_bean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_demo_list,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Demo_list_adapter.ViewHolder holder, int position) {
        holder.tv_name.setText(mList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mList.get(position).getArouter())){
                    ToastUtils.showShortToast("假装被点击了");
                }else {
                    mItemClicklisener.ItemOnclick(mList.get(position).getArouter());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
    ItemClicklisener mItemClicklisener;
    public void setItemClick(ItemClicklisener itemClicklisener){
        mItemClicklisener = itemClicklisener;
    }

    public interface ItemClicklisener{
        void ItemOnclick(String router);
    }
}
