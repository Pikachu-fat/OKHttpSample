package com.example.weijianqiang.okhttpsource;

import java.io.InputStream;

/**
 * Created by weijianqiang
 * On 2019/6/29
 * Description:
 */
public interface ICallbackListener {

    void onSucess(InputStream inputStream);

    void onFail();
}
