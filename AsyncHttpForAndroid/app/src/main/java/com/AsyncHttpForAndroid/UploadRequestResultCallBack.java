package com.AsyncHttpForAndroid;

import com.async.http.callback.UploadProgrossCallback;
import com.async.http.entity.ResponseBody;
import com.async.http.utils.LogUtils;
import com.alibaba.fastjson.JSON;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by admin on 2016-11-08.
 */
public abstract class UploadRequestResultCallBack<T>  implements UploadProgrossCallback<ResponseBody<String>> {

    @Override
    public void start() {
        requsetStart();
    }

    @Override
    public void finish() {
        requsetFinish();
    }

    @Override
    public void success(ResponseBody<String> result) {
        LogUtils.e(result.getResult());

        Class c = getClass();
        Type t1 = c.getGenericSuperclass();
        Type t2 = null;
        if (t1 instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t1).getActualTypeArguments();
            t2 = p[0];
            T  t=JSON.parseObject(result.getResult(),t2);
            requestSuccess(t);
        }else {

        }

    }

    public abstract void  requestFail(Exception e, ResponseBody<String> request);
    public abstract void  requestSuccess(T  t);
    public abstract void  requsetFinish();
    public abstract void  requsetStart();

    @Override
    public void fail(Exception e, ResponseBody<String> request) {
        requestFail(e,request);
    }
}
