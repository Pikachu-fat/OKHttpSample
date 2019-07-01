package com.example.weijianqiang.okhttpsource;

/**
 * Created by weijianqiang
 * On 2019/6/29
 * Description:
 */
public class NETHttpUtils {


    public static <T, M> void sendHttpRequest(String url, T data, Class<M> classResponse, IDataTransformListener listener) {
        JsonCallBackListener callBackListener = new JsonCallBackListener(listener, classResponse);
        JsonHttpRequest jsonHttpRequest = new JsonHttpRequest();

        HttpTask httpTask = new HttpTask(url, data, callBackListener, jsonHttpRequest);
        ThreadPoolManager.getInstance().addTask(httpTask);
    }
}
