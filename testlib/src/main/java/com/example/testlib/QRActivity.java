package com.example.testlib;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
import com.example.base_common_lib.Constant;
import com.example.base_common_lib.Utils.GlideUtils;
import com.example.base_common_lib.Utils.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import io.reactivex.functions.Consumer;


@Route( path = Arouter_path.TEST_QR_PAGE)
public class QRActivity extends BaseTitleActivity {
    Button bt_scan;
    TextView tv_result;
    ImageView iv_pic;
    @Override
    protected String setTextTitle() {
        return "二维码测试";
    }

    @Override
    protected int setToolbgcolor() {
        return R.color.white;
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
    protected int getContentView() {
        return R.layout.activity_q_r;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tv_result = findViewById(R.id.tv_result);
        iv_pic = findViewById(R.id.iv_pic);
        bt_scan = findViewById(R.id.bt_scan);
        bt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxPermissionTest();
            }
        });
    }

    @Override
    protected void initData() {

    }
    @SuppressLint("CheckResult")
    private void rxPermissionTest() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
                if (granted) {
                    // 打开相机
//                    ToastUtils.showShortToast("啊，假装打开了相机");
                    ARouter.getInstance().build(Arouter_path.TEST_SCAN_PAGE).navigation(QRActivity.this,99);
                } else {
                    // 权限被拒绝
                    ToastUtils.showShortToast("啊，相机权限被拒绝了");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 99&&resultCode == 98){
            ToastUtils.showShortToast("扫描成功");
            Bundle bundle = data.getExtras();
            String str = bundle.getString("result");
            tv_result.setText(str);
            GlideUtils.load_image(iv_pic,str);
        }
    }
}