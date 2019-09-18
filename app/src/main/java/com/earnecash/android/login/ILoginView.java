package com.earnecash.android.login;

import com.earnecash.android.login.model.LoginResponse;

public interface ILoginView {
    void onLoginSuccess(LoginResponse body);

    void onLoginFail(String s);
}
