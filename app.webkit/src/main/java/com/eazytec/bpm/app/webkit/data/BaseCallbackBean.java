package com.eazytec.bpm.app.webkit.data;

import java.util.HashMap;

/**
 * @version Id: BaseCallbackBean, v 0.1 2017-7-15 9:26 16735 Exp $$
 * @author 16735
 *
 * JS页面与本地回调的通信类
 */
public class BaseCallbackBean {

    public final String SUCCESS = "success";
    public final String ERRORMSG = "errorMsg";

    private boolean success;
    private String errorMsg;

    /**
     *  构造函数
     */
    public BaseCallbackBean() {

    }

    public BaseCallbackBean(boolean isSuccess, String errorMsg) {
        this.success = isSuccess;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public HashMap<String, String> toJson() {
        HashMap<String, String> hashMap = new HashMap<>();
        String isSuccess = this.success?"true":"false";
        hashMap.put(SUCCESS, isSuccess );
        hashMap.put(ERRORMSG, this.errorMsg);

        return hashMap;
    }
}
