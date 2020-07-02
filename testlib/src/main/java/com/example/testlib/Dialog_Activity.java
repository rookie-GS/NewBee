package com.example.testlib;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
import com.example.base_common_lib.Base.CommonAdapter;
import com.example.base_common_lib.Utils.LogUtils;
import com.example.base_common_lib.Utils.MiuiDeviceUtil;
import com.example.base_common_lib.Utils.Other_Utils;
import com.example.base_common_lib.Utils.ToastUtils;
import com.example.base_common_lib.bean.Demo_list_bean;

import java.util.ArrayList;


@Route( path = Arouter_path.TEST_DIALOG_PAGE)
public class Dialog_Activity extends BaseTitleActivity {
    RecyclerView rv_dialog_list;
    CommonAdapter list_adapter;
    ArrayList<Demo_list_bean> mlist = new ArrayList<>();
    Dialog mDialog;

    @Override
    protected String setTextTitle() {
        return "弹窗测试页面";
    }
    @Override
    protected int setTitlecolor() {
        return R.color.black;
    }
    @Override
    protected boolean setToolbvis() {
        return true;
    }
    @Override
    protected String setRight_text() {
        return "旋转";
    }

    @Override
    protected View.OnClickListener setRightlisener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果是竖排,则改为横排

                if(getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                } else if(getRequestedOrientation() ==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//如果是横排,则改为竖排
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }else if(getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_USER){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                ToastUtils.showShortToast("点击了切换屏幕");
            }
        };
    }
    @Override
    protected int setToolbgcolor() {
        return R.color.white;
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        rv_dialog_list = findViewById(R.id.rv_dialog_list);
        rv_dialog_list.setLayoutManager(new LinearLayoutManager(this));
        list_adapter = new CommonAdapter(mlist);
        rv_dialog_list.setAdapter(list_adapter);
        list_adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(mlist.get(position).getArouter().equals("ad_dialog")){
                    show_ad_dialog();
                }
                if(mlist.get(position).getArouter().equals("permision_dialog")){
                    show_permision_dialog();
                }
                if(mlist.get(position).getArouter().equals("exit_dialog")){
                    show_exit_dialog();
                }
                if(mlist.get(position).getArouter().equals("login_dialog")){
                    show_login_dialog();
                }
                if(mlist.get(position).getArouter().equals("bottom_dialog")){
                    show_bottom_dialog();
                }
                if(mlist.get(position).getArouter().equals("right_dialog")){
                    show_right_dialog();
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
        Demo_list_bean bean_03 = new Demo_list_bean();
        bean_03.setName("退出弹窗");
        bean_03.setArouter("exit_dialog");
        mlist.add(bean_03);
        Demo_list_bean bean_04 = new Demo_list_bean();
        bean_04.setName("登录弹窗");
        bean_04.setArouter("login_dialog");
        mlist.add(bean_04);

        Demo_list_bean bean_05 = new Demo_list_bean();
        bean_05.setName("底部弹窗");
        bean_05.setArouter("bottom_dialog");
        mlist.add(bean_05);
        Demo_list_bean bean_06 = new Demo_list_bean();
        bean_06.setName("侧边弹窗");
        bean_06.setArouter("right_dialog");
        mlist.add(bean_06);
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
    public void show_permision_dialog() {
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
    public void show_exit_dialog(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.exit_dialog, null);
        TextView tv_exit = view.findViewById(R.id.tv_exit);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);

        mDialog = new Dialog(mContext, R.style.dialog_bg_style);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
        tv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortToast("啊!我假装退出了");
                mDialog.dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();

            }
        });
    }
    public void show_login_dialog(){
        LogUtils.e("屏幕宽度："+Other_Utils.getScreenWidth()+"  屏幕高度："+Other_Utils.getScreenHeight());
        View view = LayoutInflater.from(mContext).inflate(R.layout.login_dialog, null);
        TextView tv_phone_login = view.findViewById(R.id.tv_phone_login);
        TextView tv_pwd_login  = view.findViewById(R.id.tv_pwd_login);
        TextView tv_swap_phone_type = view.findViewById(R.id.tv_swap_phone_type);
        TextView tv_swap_pwd_type = view.findViewById(R.id.tv_swap_pwd_type);
        LinearLayout ll_pwd_type = view.findViewById(R.id.ll_pwd_type);
        LinearLayout ll_phone_type = view.findViewById(R.id.ll_phone_type);
        mDialog = new Dialog(mContext, R.style.dialog_bg_style);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
        tv_phone_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortToast("啊!我假装登录了");
                mDialog.dismiss();
            }
        });
        tv_pwd_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortToast("啊!我假装登录了");
                mDialog.dismiss();
            }
        });
        tv_swap_phone_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ll_pwd_type.setVisibility(View.GONE);
                    ll_phone_type.setVisibility(View.VISIBLE);
            }
        });
        tv_swap_pwd_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_pwd_type.setVisibility(View.VISIBLE);
                ll_phone_type.setVisibility(View.GONE);
            }
        });

    }
    public void show_bottom_dialog(){
        mDialog = new Dialog(this,R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(this).inflate(R.layout.bottom_dialog, null);
        mDialog.setContentView(inflate);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity( Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
        mDialog.show();

    }
    public void show_right_dialog(){
        mDialog = new Dialog(this,R.style.ActionrightDialogStyle);
        View inflate = LayoutInflater.from(this).inflate(R.layout.right_dialog, null);
        mDialog.setContentView(inflate);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity( Gravity.RIGHT);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        mDialog.show();

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