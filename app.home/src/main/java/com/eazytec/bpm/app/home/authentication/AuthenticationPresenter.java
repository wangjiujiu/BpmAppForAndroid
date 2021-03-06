package com.eazytec.bpm.app.home.authentication;

import com.eazytec.bpm.app.home.R;
import com.eazytec.bpm.app.home.data.authenication.AuthenicationDataHelper;
import com.eazytec.bpm.app.home.data.authenication.AuthenticationDataTObject;
import com.eazytec.bpm.app.home.webservice.WebApi;
import com.eazytec.bpm.lib.common.RxPresenter;
import com.eazytec.bpm.lib.common.authentication.CurrentUser;
import com.eazytec.bpm.lib.common.authentication.Token;
import com.eazytec.bpm.lib.common.authentication.UserAuthority;
import com.eazytec.bpm.lib.common.authentication.UserDetails;
import com.eazytec.bpm.lib.common.webservice.BPMRetrofit;
import com.eazytec.bpm.appstub.delegate.ToastDelegate;
import com.eazytec.bpm.lib.utils.StringUtils;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * @author ConDey
 * @version Id: UserLoginPresenter, v 0.1 2017/6/2 下午4:59 ConDey Exp $$
 */
public class AuthenticationPresenter extends RxPresenter<AuthenticationContract.View> implements AuthenticationContract.Presenter<AuthenticationContract.View> {

    @Override public void userlogin(String username, String password) {

        if (StringUtils.isSpace(username)) {
            mView.toast(ToastDelegate.TOAST_TYPE_WARINGING, R.string.authentication_username_cannot_empty);
            return;
        }

        if (StringUtils.isSpace(password)) {
            mView.toast(ToastDelegate.TOAST_TYPE_WARINGING, R.string.authentication_password_cannot_empty);
            return;
        }

        Subscription rxSubscription = BPMRetrofit.retrofit().create(WebApi.class).authentication(Token.createDefaultSysToken().toString(), username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override public void call() {
                        mView.showProgress();
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override public void call() {
                        mView.dismissProgress();
                    }
                })
                .subscribe(new Observer<AuthenticationDataTObject>() {
                    @Override public void onNext(AuthenticationDataTObject data) {
                        if (data.isSuccess()) {
                            UserDetails userDetails = AuthenicationDataHelper.createUserDetailsByDTO(data);
                            UserAuthority userAuthority = AuthenicationDataHelper.createUserAuthorityByDTO(data);
                            Token token = AuthenicationDataHelper.createTokenByDTO(data);

                            CurrentUser.getCurrentUser().updateCurrentUser(userDetails, userAuthority, token);
                            mView.loginSuccess(data);
                        } else {
                            mView.toast(ToastDelegate.TOAST_TYPE_WARINGING, mView.getContext().getString(R.string.authentication_login_error) + data.getErrorMsg());
                        }
                    }

                    @Override public void onCompleted() {
                    }

                    @Override public void onError(Throwable e) {
                        mView.toast(ToastDelegate.TOAST_TYPE_ERROR, R.string.web_error);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
