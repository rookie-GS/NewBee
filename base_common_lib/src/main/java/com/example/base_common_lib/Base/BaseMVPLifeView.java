package com.example.base_common_lib.Base;

import android.content.Context;

import com.trello.rxlifecycle4.LifecycleTransformer;

public interface BaseMVPLifeView  {
    Context onContext();

    LifecycleTransformer onBindToLifecycle();

    void onError();
}
