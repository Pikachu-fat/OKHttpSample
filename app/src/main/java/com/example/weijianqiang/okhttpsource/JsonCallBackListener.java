package com.example.weijianqiang.okhttpsource;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by weijianqiang
 * On 2019/6/29
 * Description:
 */
public class JsonCallBackListener<T> implements ICallbackListener {

    private IDataTransformListener dataTransformListener;
    private Class<T> aClass;
    private Handler handler = new Handler(Looper.getMainLooper());

    public JsonCallBackListener(IDataTransformListener<T> dataTransformListener, Class<T> tClass) {
        this.dataTransformListener = dataTransformListener;
        this.aClass = tClass;
    }

    @Override
    public void onSucess(InputStream inputStream) {
        String content = getContent(inputStream);
        final T restltEntity = JSON.parseObject(content, aClass);
        handler.post(new Runnable() {
            @Override
            public void run() {
                dataTransformListener.transformData(restltEntity, 0);
            }
        });
    }

    @Override
    public void onFail() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                dataTransformListener.transformData(null, 1);
            }
        });
    }

    private String getContent(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String line = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            while ((line = bufferedReader.readLine()) != null)
                sb.append(line + "\n");
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
