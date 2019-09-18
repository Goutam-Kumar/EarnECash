package com.earnecash.android.apipresenter;

import com.earnecash.android.login.model.LoginResponse;
import com.earnecash.android.register.model.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> attemptLogin(@Field("email") String email, @Field("password") String password);

    @POST("register.php")
    @FormUrlEncoded
    Call<RegisterResponse> attemptRegister(@Field("name") String name,@Field("email") String email,@Field("password") String password,@Field("phone") String mobile,@Field("profession") String profession);
}
