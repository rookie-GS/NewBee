package com.example.base_common_lib.Utils.NetUtils.converter;

//import com.facebook.stetho.server.http.HttpStatus;
import com.example.base_common_lib.Base.BaseModel.BaseHttpResult;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * 解析返回数据
 * Created by LMY on 2018/3/25.
 */

final class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        BaseHttpResult httpStatus = gson.fromJson(response, BaseHttpResult.class);
        if (httpStatus.code != 200) {
            value.close();
            throw new ApiException(httpStatus.code, httpStatus.message);
        }else {
            if (httpStatus.result == null) {
                BaseHttpResult baseHttpResult = new BaseHttpResult();
                baseHttpResult.result = new Object();
                baseHttpResult.code = httpStatus.code;
                baseHttpResult.message = httpStatus.message;
                response = gson.toJson(baseHttpResult);
            }
            try {
                return adapter.fromJson(response);
            } finally {
                value.close();
            }
        }
    }
}
