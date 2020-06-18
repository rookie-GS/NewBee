package com.example.base_common_lib.Base.BaseModel;
/**
 * @Author G_JS
 * retrofit网络框架请求
 * @Date Created by 2020/6/12 11:40
 * 
 */
public class BaseHttpResult<T> {
    public String message;
    public int code;
    public T result;
}
