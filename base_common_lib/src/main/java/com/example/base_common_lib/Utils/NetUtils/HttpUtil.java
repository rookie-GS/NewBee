package com.example.base_common_lib.Utils.NetUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;

import com.example.base_common_lib.Base.BaseModel.BaseHttpResult;
import com.example.base_common_lib.Utils.NetUtils.rx.RxSchedulersHelper;
import com.orhanobut.hawk.Hawk;
import com.trello.rxlifecycle4.LifecycleTransformer;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HttpUtil {

    /**
     * 构造方法私有
     */
    private HttpUtil() {
    }

    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

    /**
     * 获取单例
     */
    public static HttpUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Observable observable;
        private boolean isShowDialog;
        private String cacheKey;
        private boolean isRefreshData;
        private LifecycleTransformer bindLifecycle;
        private Context mContext;
        private long delayTime;
        private long delayRefreshData;
        private WeakReference<Context> weakReference;

        public Builder() {
        }

        /**
         * 创建请求体
         */
        public Builder create(Observable observable) {
            this.observable = observable;
            return this;
        }

        public Builder bindLifecycle(LifecycleTransformer bindLifecycle) {
            this.bindLifecycle = bindLifecycle;
            return this;
        }

        /**
         * 是否显示dialog 网络加载等待进度
         */
        public Builder showProgress(boolean isShowDialog, Context context) {
            this.isShowDialog = isShowDialog;
            this.mContext = context;
            return this;
        }

        /**
         * 是否显示dialog 网络加载等待进度
         */
        public Builder showProgress(Context context) {
            this.mContext = context;
//            weakReference = new WeakReference<>(context);
//            this.mContext = weakReference.get();
            return this;
        }

        /**
         * 是否缓存data
         */
        public Builder cacheData(String cacheKey) {
            this.cacheKey = cacheKey;
            return this;
        }

        /**
         * 是否强制刷新数据
         */
        public Builder refreshData(boolean isRefreshData) {
            this.isRefreshData = isRefreshData;
            return this;
        }

        /**
         * 延迟加载
         * @param delayTime 延迟时间 毫秒
         */
        public Builder delayTime(long delayTime){
            this.delayTime = delayTime;
            return this;
        }

        @SuppressLint("CheckResult")
        public void subscriber(final ProgressSubscriber subscriber) {
            ObservableTransformer<BaseHttpResult<Object>, Object> transformer;
            if (delayTime == 0) {
                transformer = RxHelper.handleResult();
            }else {
                transformer = RxHelper.handleResult(delayTime);
            }
            final Observable onComplete = observable.compose(transformer)
                    .compose(RxSchedulersHelper.ioMain())
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
                            subscriber.onSubscribe(disposable);

                        }
                    });
            onComplete.subscribe(new Consumer() {
                @Override
                public void accept(Object o) throws Exception {
                    subscriber.onNext(o);
                    if (!TextUtils.isEmpty(cacheKey)) {
                        Hawk.put(cacheKey, o);
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    if (!TextUtils.isEmpty(cacheKey)) {
                        cacheGetData(subscriber, onComplete);
                    }else {
                        subscriber.onError(throwable);
                    }

//                    subscriber.dismissProgressDialog();
                }
            }, new Action() {
                @Override
                public void run() throws Exception {
                    subscriber.onComplete();
                }
            });
        }

        @SuppressLint("CheckResult")
        public void cacheGetData(final ProgressSubscriber subscriber, Observable onComplete){
            //缓存处理 分开写了，如果没有缓存key，没必要进load方法。
            RetrofitCache.load(cacheKey, onComplete, isRefreshData)
                    .subscribe(new Consumer() {
                        @Override
                        public void accept(Object o) throws Exception {
                            subscriber.onNext(o);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            subscriber.onError(throwable);
//                            subscriber.dismissProgressDialog();
                        }
                    }, new Action() {
                        @Override
                        public void run() throws Exception {
                            subscriber.onComplete();
                        }
                    });
        }
    }

    /**
     * 需要回调
     *
     * @param callRequest 回调的类
     */
    @SuppressLint("CheckResult")
    public void toSubscribe(Observable ob, final CallRequest callRequest) {
        ob.subscribeOn(Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        try {
                            callRequest.onSuccess(o);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        try {
                            callRequest.onError(throwable);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 不需要回调
     */
    @SuppressLint("CheckResult")
    public void toSubscribe(Observable ob) {
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });

    }
}

