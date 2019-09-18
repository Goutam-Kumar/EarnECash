package com.earnecash.android.login;

import com.earnecash.android.appinstance.EarnECash;
import com.earnecash.android.login.model.LoginResponse;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements ILoginPresenter{
    ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    @Override
    public void attemptLogin(String email, String password) {
        JsonObject loginRequest = new JsonObject();
        loginRequest.addProperty("email",email);
        loginRequest.addProperty("password",password);
        EarnECash.getInstance().restApi.attemptLogin(email,password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                iLoginView.onLoginSuccess(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                iLoginView.onLoginFail("Some error found. Please try again later.");
            }
        });
    }
}
