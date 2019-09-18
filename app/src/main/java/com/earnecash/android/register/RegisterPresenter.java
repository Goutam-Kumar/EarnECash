package com.earnecash.android.register;

import android.util.Log;
import com.earnecash.android.appinstance.EarnECash;
import com.earnecash.android.register.model.RegisterResponse;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements IRegisterPresenter {
    IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
    }

    @Override
    public void attemptRegister(String name, String email, String password, String mobile, String profession) {
        EarnECash.getInstance().restApi.attemptRegister(name,email,password,mobile,profession).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Log.d("Request",response.raw().request().toString());
                Log.d("OnSuccess",response.body().toString());
                iRegisterView.onSuccessRegister(response.body());
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                iRegisterView.onRegisterFail("Some error found. Please try again later.");
            }
        });
    }
}
