package com.example.testlib;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
import com.example.base_common_lib.Image_Url;
import com.example.base_common_lib.Utils.GlideUtils;
import com.example.base_common_lib.Utils.LogUtils;
import com.example.base_common_lib.Utils.ToastUtils;
import com.example.testlib.adapter.ImageRecyclerAdapter;
import com.example.testlib.bean.ImageRecyclerBean;

import java.util.ArrayList;
@Route(path = Arouter_path.TEST_RECYCLERVIEW_PAGE)
public class RecyclerViewActivity extends BaseTitleActivity implements View.OnClickListener {
    RecyclerView rl_list;
    ImageRecyclerAdapter adapter;
    ArrayList<ImageRecyclerBean> beanArrayList = new ArrayList<>();
    ArrayList<ImageRecyclerBean> beanArrayList02 = new ArrayList<>();
    ArrayList<View> header_list = new ArrayList<>();
    ArrayList<View> footer_list = new ArrayList<>();
    int count = 0;
    boolean switch_list = true;
    Button bt_change_item,bt_reset,bt_add_header,bt_remove_header,bt_add_footer,bt_remove_footer,bt_remove_item,bt_add_item;
    @Override
    protected boolean setToolbvis() {
        return true;
    }

    @Override
    protected String setTextTitle() {
        return "列表测试";
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
        return R.layout.activity_recycler_view;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        bt_add_item = findViewById(R.id.bt_add_item);
        bt_add_item.setOnClickListener(this);
        bt_remove_item = findViewById(R.id.bt_remove_item);
        bt_remove_item.setOnClickListener(this);
        bt_add_footer = findViewById(R.id.bt_add_footer);
        bt_add_footer.setOnClickListener(this);
        bt_remove_footer = findViewById(R.id.bt_remove_footer);
        bt_remove_footer.setOnClickListener(this);
        bt_remove_header = findViewById(R.id.bt_remove_header);
        bt_remove_header.setOnClickListener(this);
        bt_add_header = findViewById(R.id.bt_add_header);
        bt_add_header.setOnClickListener(this);
        bt_change_item = findViewById(R.id.bt_change_item);
        bt_reset = findViewById(R.id.bt_reset);
        bt_reset.setOnClickListener(this);
        bt_change_item.setOnClickListener(this);
        rl_list = findViewById(R.id.rl_list);
        rl_list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ImageRecyclerAdapter(beanArrayList);
        adapter.setEmptyView(addEmptyView());
        rl_list.setAdapter(adapter);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == bt_change_item.getId()){
            if(adapter.getData().size() !=0){
                ImageRecyclerBean replacebean = new ImageRecyclerBean();
                replacebean.setImage(Image_Url.kobe_image_url);
                if(count<adapter.getData().size()){
                    adapter.setData(count,replacebean);
                    count = count+1;
                }
            }else {
                ToastUtils.showShortToast("当前列表为空");
            }

        }
        if(v.getId() == bt_reset.getId()){
            adapter.removeAllFooterView();
            adapter.removeAllHeaderView();
            beanArrayList.clear();
            adapter.notifyDataSetChanged();
        }
        if(v.getId() == bt_add_header.getId()){
            if(beanArrayList.size()!=0){
                 adapter.addHeaderView(addHeadView());
            }else {
                ToastUtils.showShortToast("列表没有内容");
            }
        }
        if(v.getId() == bt_remove_header.getId()){
            if(header_list.size()!=0){
                adapter.removeHeaderView(header_list.get(0));
                header_list.remove(0);
            }else {
                ToastUtils.showShortToast("没有头布局了");
            }
        }
        if(v.getId() == bt_add_footer.getId()){
            if(beanArrayList.size()!=0){
                adapter.addFooterView(addFootView());
            }else {
                ToastUtils.showShortToast("列表没有内容");
            }
        }
        if(v.getId() == bt_remove_footer.getId()){
            if(footer_list.size()!=0){
                adapter.removeFooterView(footer_list.get(0));
                footer_list.remove(0);
            }else {
                ToastUtils.showShortToast("没有尾部局了");
            }
        }
        if(v.getId() == bt_add_item.getId()){
            ImageRecyclerBean add_bean = new ImageRecyclerBean();
            add_bean.setImage(Image_Url.Kd_image_url);
            adapter.addData(add_bean);
        }
        if (v.getId() == bt_remove_item.getId()){
                if(beanArrayList.size()!=0){
                    adapter.remove(beanArrayList.get(beanArrayList.size()-1));
                    LogUtils.e(beanArrayList.size()+"  数据个数");
                }else {
                ToastUtils.showShortToast("不能再删了");
                }
        }
    }
    public View addHeadView(){
        View headerview = LayoutInflater.from(this).inflate(R.layout.head_view,null);
        header_list.add(headerview);
        return  headerview;
    }
    public View addFootView(){
        View footerview = LayoutInflater.from(this).inflate(R.layout.foot_view,null);
        footer_list.add(footerview);
        return  footerview;
    }
    public View addEmptyView(){
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view,null);
        return  emptyView;
    }
}