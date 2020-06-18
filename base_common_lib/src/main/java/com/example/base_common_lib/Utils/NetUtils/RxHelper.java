package com.example.base_common_lib.Utils.NetUtils;
import com.example.base_common_lib.Base.BaseModel.BaseHttpResult;
import com.example.base_common_lib.Utils.NetUtils.converter.ApiException;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * 处理数据
 * Created by lmy on 2017/7/11
 */

public class RxHelper {
    /**
     * 数据处理
     */
    public static <T> ObservableTransformer<BaseHttpResult<T>, T> handleResult() {
        return upstream -> upstream.flatMap((Function<BaseHttpResult<T>, ObservableSource<T>>) tBaseHttpResult -> {
            if (tBaseHttpResult.code == 200) {
                return createData(tBaseHttpResult.result == null ? (T)"" : tBaseHttpResult.result);
            } else {
                return Observable.error(new ApiException(tBaseHttpResult.code, tBaseHttpResult.message));
            }
        })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<BaseHttpResult<T>, T> handleResult(final long delayTime) {
        return upstream -> upstream.flatMap((Function<BaseHttpResult<T>, ObservableSource<T>>) tBaseHttpResult -> {
            if (tBaseHttpResult.code == 100) {
                return createData(tBaseHttpResult.result == null ? (T)"" : tBaseHttpResult.result);
            } else {
                return Observable.error(new ApiException(tBaseHttpResult.code, tBaseHttpResult.message));
            }
        })      .delay(delayTime, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }



    /**
     * rcode == 1 发送数据
     *
     * @param data 成功的数据
     * @param <T>  类型
     */
    private static <T> Observable<T> createData(final T data) {

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                try {
                    subscriber.onNext(data);
                    subscriber.onComplete();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
