package com.example.base_common_lib.Utils.NetUtils.rx;

import com.example.base_common_lib.Utils.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 接口适配器，使用抽象统一处理onError
 * Created by on 2018/8/16.
 *
 * @author lmy
 */
public abstract class RxInterfaceObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.showShortToast("操作失败");
    }

    @Override
    public void onComplete() {

    }
}
