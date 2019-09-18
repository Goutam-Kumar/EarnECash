package com.earnecash.android.appinstance;


import android.content.Context;
import android.support.multidex.MultiDexApplication;
import com.earnecash.android.apipresenter.RestApi;
import com.earnecash.android.network.ApiConstants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class EarnECash extends MultiDexApplication {
    private static EarnECash mInstance = null;
    public static Context context;
    public RestApi restApi;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = this;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .writeTimeout(15000, TimeUnit.MILLISECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restApi = retrofit.create(RestApi.class);
    }

    public static synchronized EarnECash getInstance() {
        return mInstance;
    }
}
