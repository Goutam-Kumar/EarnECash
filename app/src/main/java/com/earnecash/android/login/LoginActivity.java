package com.earnecash.android.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.earnecash.android.R;
import com.earnecash.android.apphelper.AppHelper;
import com.earnecash.android.home.HomeActivity;
import com.earnecash.android.login.model.LoginResponse;
import com.earnecash.android.login.model.UserData;
import com.earnecash.android.register.RegisterActivity;
import com.earnecash.android.widget.BoldTextView;
import com.earnecash.android.widget.RegularEditText;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements ILoginView{
    private final String TAG = LoginActivity.this.getClass().getSimpleName();
    @BindView(R.id.tvUsername)
    RegularEditText tvUsername;
    @BindView(R.id.tvPassword)
    RegularEditText tvPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.chkRemember)
    AppCompatCheckBox chkRemember;
    @BindView(R.id.tvRegisterNow)
    BoldTextView tvRegisterNow;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    boolean loginCheck = false;
    LoginPresenter iLoginPresenter;
    AlertDialog.Builder builder;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        iLoginPresenter = new LoginPresenter(this);
        AppHelper.myTextWatcher(LoginActivity.this,tvUsername);
        AppHelper.myTextWatcher(LoginActivity.this,tvPassword);
        builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.progress);
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        chkRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    loginCheck = true;
                }else {
                    loginCheck = false;
                }
            }
        });
    }

    @OnClick({R.id.btnLogin, R.id.tvRegisterNow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if (validateAll()){
                    if (!dialog.isShowing())
                        dialog.show();
                    iLoginPresenter.attemptLogin(tvUsername.getText().toString(), tvPassword.getText().toString());
                }
                break;
            case R.id.tvRegisterNow:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
        }
    }

    private boolean validateAll() {
        boolean valid = true;
        if (TextUtils.isEmpty(tvUsername.getText().toString())){
            AppHelper.errorBox(LoginActivity.this,tvUsername,"Please enter username.");
            valid = false;
        }else if (TextUtils.isEmpty(tvPassword.getText().toString())){
            AppHelper.errorBox(LoginActivity.this,tvPassword,"Please enter password.");
            valid = false;
        }
        return valid;
    }

    @Override
    public void onLoginSuccess(LoginResponse loginResponse) {
        AppHelper.setDialog(LoginActivity.this,false);
        if (dialog.isShowing())
            dialog.dismiss();
        if (loginResponse.getResult() == 0){
            AppHelper.makeToast(LoginActivity.this,"Email or Password not found.");
        }else if (loginResponse.getResult() == 1){
            AppHelper.LoggerD(TAG,loginResponse.getUserData().toString());
            UserData user = loginResponse.getUserData();
            String userData = new Gson().toJson(user,UserData.class);
            AppHelper.saveString(AppHelper.USER_PREF,AppHelper.USER_DATA,userData);
            AppHelper.saveBoolean(AppHelper.USER_PREF,AppHelper.ISLOGIN,true);

            /*if (loginCheck){
                AppHelper.saveBoolean(AppHelper.USER_PREF,AppHelper.ISLOGIN,true);
            }else {
                AppHelper.saveBoolean(AppHelper.USER_PREF,AppHelper.ISLOGIN,false);
            }*/
            startActivity(new Intent(LoginActivity.this,HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }else {
            AppHelper.makeToast(LoginActivity.this,loginResponse.getMsg());
        }
    }

    @Override
    public void onLoginFail(String s) {
        if (dialog.isShowing())
            dialog.dismiss();
        AppHelper.makeToast(LoginActivity.this,s);
    }
}
