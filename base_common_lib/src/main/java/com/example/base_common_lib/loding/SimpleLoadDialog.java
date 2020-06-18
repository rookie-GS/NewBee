package com.example.base_common_lib.loding;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.example.base_common_lib.R;
import com.example.base_common_lib.Utils.NetUtils.ProgressCancelListener;
import com.example.base_common_lib.Utils.Other_Utils;


public class SimpleLoadDialog {

    private Dialog load;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;
    private DetachableCancelListener cancelListener;
    private PPTVLoading ppLoading;

    public SimpleLoadDialog(Context context, ProgressCancelListener mProgressCancelListener,
                            boolean cancelable) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
        createDialog();
    }

    public SimpleLoadDialog(Context context,
                            boolean cancelable) {
        super();
        this.context = context;
        this.cancelable = cancelable;
        createDialog();
    }

    public void createDialog() {
        load = new Dialog(context, R.style.Dialog2);
        View dialogView = LayoutInflater.from(context).inflate(
                R.layout.custom_sload_layout, null);

        ppLoading = dialogView.findViewById(R.id.pp_loading);
        load.setCanceledOnTouchOutside(false);
        load.setCancelable(false);
        load.setCancelable(cancelable);
        load.setContentView(dialogView);

//        cancelListener = DetachableCancelListener.wrap(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                ppLoading.clear();
//                if (mProgressCancelListener != null) {
//                    mProgressCancelListener.onCancelProgress();
//                }
//                if (cancelListener != null) {
//                    cancelListener.clearOnDetach();
//                    cancelListener = null;
//                }
//            }
//        });
//        load.setOnCancelListener(cancelListener);

//        load.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                ppLoading.clear();
//                if (mProgressCancelListener != null) {
//                    mProgressCancelListener.onCancelProgress();
//                }
//            }
//        });
        Window dialogWindow = load.getWindow();
        if (dialogWindow != null) {
            dialogWindow.setGravity(Gravity.CENTER);
            dialogWindow.setLayout(Other_Utils.dip2px(60), Other_Utils.dip2px(40));
        }
    }

    public void showDialog() {
        if (load != null && !((Activity) context).isFinishing()) {
            load.show();
        }
    }

    public void dismiss() {
        ppLoading.clear();
        if (load != null && load.isShowing() && !((Activity) context).isFinishing()) {
            if (mProgressCancelListener != null) {
//                    mProgressCancelListener.onCancelProgress();
                mProgressCancelListener = null;
            }
            load.cancel();
            load = null;
        }
    }
}
