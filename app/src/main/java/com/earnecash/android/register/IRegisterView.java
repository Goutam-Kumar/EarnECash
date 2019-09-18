package com.earnecash.android.register;

import com.earnecash.android.register.model.RegisterResponse;

public interface IRegisterView {
    void onSuccessRegister(RegisterResponse registerResponse);

    void onRegisterFail(String msg);
}
