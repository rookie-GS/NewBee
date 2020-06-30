package com.example.testlib;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
import com.example.base_common_lib.Constant;
import com.example.base_common_lib.Utils.LogUtils;
import com.example.base_common_lib.Utils.ToastUtils;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.List;


@Route( path = Arouter_path.TEST_PIC_PAGE)
public class Pic_selector_Activity extends BaseTitleActivity {
    private RecyclerView mRecyclerView;
    private PopupWindow pop;
    private TextView tv_select,tv_path;

    @Override
    protected String setTextTitle() {
        return "图片选择器测试";
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
        return R.layout.activity_pic_selector;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mRecyclerView = findViewById(R.id.recycler);
        tv_select = findViewById(R.id.tv_select);
        tv_path = findViewById(R.id.tv_path);
        tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop();
            }
        });

    }
    @Override
    protected void initData() {

    }

    private void showPop() {
        View bottomView = View.inflate(Pic_selector_Activity.this, R.layout.layout_bottom_dialog, null);
        TextView mAlbum = bottomView.findViewById(R.id.tv_album);
        TextView mCamera = bottomView.findViewById(R.id.tv_camera);
        TextView mCancel = bottomView.findViewById(R.id.tv_cancel);

        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.tv_album) {//相册
                    Matisse.from(Pic_selector_Activity.this)
                            .choose(MimeType.ofAll())
                            .countable(true)
                            .maxSelectable(9)
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f)
                            .imageEngine(new GlideEngine())
                            .showPreview(false) // Default is `true`
                            .forResult(Constant.REQUEST_CODE_CHOOSE);
                    ToastUtils.showShortToast("假装弹出相册了");
                } else if (id == R.id.tv_camera) {//拍照
//                    PictureSelector.create(Pic_selector_Activity.this)
//                            .openCamera(PictureMimeType.ofImage())
//                            .forResult(PictureConfig.CHOOSE_REQUEST);
                    ToastUtils.showShortToast("假装拍照了");

                } else if (id == R.id.tv_cancel) {//取消
                    //closePopupWindow();
                }
                closePopupWindow();
            }
        };

        mAlbum.setOnClickListener(clickListener);
        mCamera.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
    }
    public void closePopupWindow() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri>  mSelected = Matisse.obtainResult(data);
            LogUtils.e("图片路径:"+mSelected.toString());
        }
    }
}