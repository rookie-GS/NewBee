package com.example.base_common_lib.Base;

import java.lang.ref.WeakReference;

/**
 * @Author G_JS
 * P层父类
 * @Date Created by 2020/6/11 16:48
 *
 */
public abstract class BasePresenter<T> {
    /**
     * 持有UI接口的弱引用
     */
    protected WeakReference<T> mViewRef;

    /**
     * 获取数据方法
     */
    public abstract void requestData();

    public void requestData(boolean isShowDialog){

    }

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    /**
     * 解绑
     */
    public void detach()
    {
        if(mViewRef!=null)
        {
            mViewRef.clear();
            mViewRef=null;
        }
    }
}
