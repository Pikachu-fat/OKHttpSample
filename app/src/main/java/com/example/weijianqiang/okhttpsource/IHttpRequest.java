package com.example.weijianqiang.okhttpsource;

/**
 * Created by weijianqiang
 * On 2019/6/29
 * Description:
 */
public interface IHttpRequest {

    void setUrl(String url);

    void setData(byte[] data);

    void setCallbackListener(ICallbackListener callbackListener);

    void execute();
}
