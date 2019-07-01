package com.example.weijianqiang.okhttpsource;

/**
 * Created by weijianqiang
 * On 2019/6/29
 * Description:
 */
public interface IDataTransformListener<T> {

    void transformData(T data,int code);
}
