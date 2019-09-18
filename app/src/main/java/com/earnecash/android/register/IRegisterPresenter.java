package com.earnecash.android.register;

public interface IRegisterPresenter {
    void attemptRegister(String name, String email, String password, String mobile, String profession);
}
