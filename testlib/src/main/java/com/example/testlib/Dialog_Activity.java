package com.example.testlib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
import com.example.base_common_lib.Utils.MiuiDeviceUtil;
import com.example.base_common_lib.Utils.Other_Utils;
import com.example.base_common_lib.bean.Demo_list_bean;

import java.util.ArrayList;


@Route( path = Arouter_path.TEST_DIALOG_PAGE)
public class Dialog_Activity extends BaseTitleActivity {
    RecyclerView rv_dialog_list;
    Demo_list_adapter list_adapter;
    ArrayList<Demo_list_bean> mlist = new ArrayList<>();
    Dialog mDialog;

    @Override
    protected String setTextTitle() {
        return "弹窗测试页面";
    }

    @Override
    protected boolean setToolbvis() {
        return true;
    }

    @Override
    protected int setToolbgcolor() {
        return R.color.lightgrey;
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        rv_dialog_list = findViewById(R.id.rv_dialog_list);
        rv_dialog_list.setLayoutManager(new LinearLayoutManager(this));
        list_adapter = new Demo_list_adapter(mlist);
        rv_dialog_list.setAdapter(list_adapter);
        list_adapter.setItemClick(new Demo_list_adapter.ItemClicklisener() {
            @Override
            public void ItemOnclick(String router) {
                if(router.equals("ad_dialog")){
                    show_ad_dialog();
                }
                if(router.equals("permision_dialog")){
                    show_permision_dialog();
                }
            }
        });
    }

    @Override
    protected void initData() {
        Demo_list_bean bean = new Demo_list_bean();
        bean.setName("广告弹窗");
        bean.setArouter("ad_dialog");
        mlist.add(bean);
        Demo_list_bean bean_02 = new Demo_list_bean();
        bean_02.setName("权限弹窗");
        bean_02.setArouter("permision_dialog");
        mlist.add(bean_02);
        list_adapter.notifyDataSetChanged();
    }

    public void show_ad_dialog(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.huodong_dialog, null);
        ImageView iv_huodong = view.findViewById(R.id.iv_huodong);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        if (mContext != null) {
            Glide.with(mContext).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592539403248&di=80b8fc563e2f28daa1f89df9d8422e68&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheji%2F20151210%2Fgaodiaolansefangdichanmaidianguanggaopai_5525368.jpg").into(iv_huodong);
        }
        mDialog = new Dialog(mContext, R.style.dialog_bg_style);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        Window window = mDialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = (int) (Other_Utils.getScreenHeight() * 0.7);
        windowparams.width = (int) (Other_Utils.getScreenWidth()  * 0.8);
        mDialog.show();
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

    }
    public void show_permision_dialog(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.quanxian_dialog, null);
        TextView tv_close = view.findViewById(R.id.tv_quanxain_close);
        TextView tv_goto_set = view.findViewById(R.id.tv_goto_set);

        mDialog = new Dialog(mContext, R.style.dialog_bg_style);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
//        Window window = mDialog.getWindow();
//        WindowManager.LayoutParams windowparams = window.getAttributes();
//        windowparams.height = (int) (Other_Utils.getScreenHeight() * 0.7);
//        windowparams.width = (int) (Other_Utils.getScreenWidth()  * 0.8);
        mDialog.show();
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        tv_goto_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingPermission(mContext);
            }
        });
    }
    /**
     * 打开权限设置界面
     */
    public static void openSettingPermission(Context context) {
        try {
            if(MiuiDeviceUtil.isMiui()){
                MiuiDeviceUtil.openMiuiPermissionActivity(context);
            }else{
                Intent intent1 = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent1.setData(uri);
                context.startActivity(intent1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}