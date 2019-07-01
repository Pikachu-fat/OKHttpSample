package com.example.weijianqiang.okhttpsource;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by weijianqiang
 * On 2019/6/29
 * Description:
 */
public class JsonHttpRequest implements IHttpRequest {

    private String url;
    private byte[] bytes;
    private ICallbackListener callbackListener;
    private HttpURLConnection httpURLConnection;


    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setData(byte[] data) {
        this.bytes = bytes;
    }

    @Override
    public void setCallbackListener(ICallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }

    @Override
    public void execute() {
        URL url = null;
        try {
            url = new URL(this.url);
            httpURLConnection = (HttpURLConnection) (url.openConnection());
            httpURLConnection.setConnectTimeout(6000);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setReadTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type","application/jsom;charset=UTF-8");
            httpURLConnection.connect();

//            OutputStream outputStream = httpURLConnection.getOutputStream();
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
//            bufferedOutputStream.write(bytes);
//            bufferedOutputStream.flush();
//            outputStream.close();
//            bufferedOutputStream.close();

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = httpURLConnection.getInputStream();
                callbackListener.onSucess(inputStream);
            } else {
                callbackListener.onFail();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
