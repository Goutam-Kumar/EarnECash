package com.earnecash.android;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.earnecash.android.apphelper.AppHelper;
import com.earnecash.android.home.HomeActivity;
import com.earnecash.android.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int TIMEOUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        AppHelper.getLightTheme(SplashActivity.this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AppHelper.getBoolean(AppHelper.USER_PREF,AppHelper.ISLOGIN)){
                    startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }

            }
        },TIMEOUT);
    }
}
