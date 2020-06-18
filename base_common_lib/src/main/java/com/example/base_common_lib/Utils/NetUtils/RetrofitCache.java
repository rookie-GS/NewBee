package com.example.base_common_lib.Utils.NetUtils;

import com.orhanobut.hawk.Hawk;

import java.net.SocketException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * 缓存数据
 * Created by lmy on 2017/7/11
 */

class RetrofitCache {
    /**
     * @param cacheKey     缓存的Key
     * @param fromNetwork
     * @param forceRefresh 是否强制刷新
     */
    static <T> Observable<T> load(final String cacheKey,
                                  Observable<T> fromNetwork,
                                  boolean forceRefresh) {
        Observable<T> fromCache = Observable.create((ObservableOnSubscribe<T>) subscriber -> {
            T cache = Hawk.get(cacheKey);
            if (cache != null) {
                if (cache instanceof List) {
                    if (((List) cache).size() == 0) {
                        subscriber.onError(new SocketException());
                    }else {
                        subscriber.onNext(cache);
                    }
                }else {
                    subscriber.onNext(cache);
                }
            } else {
                subscriber.onError(new SocketException());
//                    subscriber.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        //缓存cacheKey 如果不为null，就缓存数据
//        if (cacheKey != null) {
//            fromNetwork = fromNetwork.map(new Function<T, T>() {
//                @Override
//                public T apply(T result) throws Exception {
//                    Hawk.put(cacheKey, result);
//                    return result;
//                }
//            });
//        }

        //强制刷新
        if (forceRefresh) {
            return fromNetwork;
        } else {
            return Observable.concat(fromCache, fromNetwork).filter(new Predicate<T>() {
                @Override
                public boolean test(T t) throws Exception {
                    return t != null;
                }
            });
        }
    }
}
