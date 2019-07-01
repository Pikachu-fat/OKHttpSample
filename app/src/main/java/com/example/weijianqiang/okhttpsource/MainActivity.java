package com.example.weijianqiang.okhttpsource;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public String url = "http://v.juhe.cn/historyWeather/citys?province_id=2&key=bb52107206585ab074f5e59abc73875b";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NETHttpUtils.sendHttpRequest(url, null, ResponseClass.class, new IDataTransformListener<ResponseClass>() {
            @Override
            public void transformData(ResponseClass data, int code) {
                Log.e(TAG, "transformData: "+data.toString() );
            }
        });
    }
}
