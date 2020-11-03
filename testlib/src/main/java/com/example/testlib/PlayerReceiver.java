package com.example.testlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Utils.LogUtils;
import com.example.base_common_lib.Utils.ToastUtils;

import static com.example.base_common_lib.Utils.ToastUtils.*;

/**
 * 作者：G_JS
 * 时间：
 */
public class PlayerReceiver extends BroadcastReceiver {


    public PlayerReceiver() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();// 获取对应Action
        LogUtils.e("收到了广播"+action);

//        ARouter.getInstance().build(action).navigation();
        showShortToast(action);
    }

}
