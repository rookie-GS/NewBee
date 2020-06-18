package com.example.base_common_lib.Base;
import android.content.Context;

import com.example.base_common_lib.Utils.ActivityLifeCycleEvent;

import io.reactivex.subjects.PublishSubject;


/**
 * @Author G_JS
 * M层接口回调
 * @Date Created by 2020/6/12 14:55
 *
 */
public interface BaseRequestListener {
    Context getContexts();

    PublishSubject<ActivityLifeCycleEvent> getLifeCycleEvent();

    void onRequestErr();
}