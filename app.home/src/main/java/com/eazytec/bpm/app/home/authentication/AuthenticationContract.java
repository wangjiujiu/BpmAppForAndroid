package com.eazytec.bpm.app.home.authentication;

import com.eazytec.bpm.app.home.data.authenication.AuthenticationDataTObject;
import com.eazytec.bpm.lib.common.CommonContract;

/**
 * @author ConDey
 * @version Id: UserLoginContract, v 0.1 2017/6/2 下午3:44 ConDey Exp $$
 */
public interface AuthenticationContract {

    interface View extends CommonContract.CommonView {

        // 跳转页面成功后执行的方法
        void loginSuccess(AuthenticationDataTObject loginData);
    }

    interface Presenter<T> extends CommonContract.CommonPresenter<T> {

        void userlogin(String username, String password);
    }
}
