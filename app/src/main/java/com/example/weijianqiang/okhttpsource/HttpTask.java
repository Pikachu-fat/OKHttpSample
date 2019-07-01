package com.example.weijianqiang.okhttpsource;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * Created by weijianqiang
 * On 2019/6/29
 * Description:
 */
public class HttpTask<T> implements Runnable{

    private IHttpRequest httpRequest = null;

    public HttpTask(String url,T data,ICallbackListener listener,IHttpRequest request){
        httpRequest = request;
        request.setUrl(url);
        request.setCallbackListener(listener);
        String content = JSON.toJSONString(data);
        try {
            request.setData(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        httpRequest.execute();
    }
}
