package com.example.weijianqiang.okhttpsource;

/**
 * Created by weijianqiang
 * On 2019/6/29
 * Description:
 */
public class ResponseClass {

    private int resultcode;
    private String reason;

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ResponseClass{" +
                "resultcode=" + resultcode +
                ", reason='" + reason + '\'' +
                '}';
    }
}
