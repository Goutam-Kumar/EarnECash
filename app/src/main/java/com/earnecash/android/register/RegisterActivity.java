package com.earnecash.android.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.earnecash.android.R;
import com.earnecash.android.apphelper.AppHelper;
import com.earnecash.android.login.LoginActivity;
import com.earnecash.android.register.model.RegisterResponse;
import com.earnecash.android.widget.BoldTextView;
import com.earnecash.android.widget.RegularEditText;
import com.earnecash.android.widget.RegularTextView;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements IRegisterView{

    @BindView(R.id.tvFullName)
    RegularEditText tvFullName;
    @BindView(R.id.tvEmail)
    RegularEditText tvEmail;
    @BindView(R.id.tvPassword)
    RegularEditText tvPassword;
    @BindView(R.id.tvConfirmPassword)
    RegularEditText tvConfirmPassword;
    @BindView(R.id.tvMobile)
    RegularEditText tvMobile;
    @BindView(R.id.spinnerProfession)
    Spinner spinnerProfession;
    @BindView(R.id.tvProfession)
    RegularTextView tvProfession;
    @BindView(R.id.tvOtherProfession)
    RegularEditText tvOtherProfession;
    @BindView(R.id.tvRef)
    RegularEditText tvRef;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.tvLoginNow)
    BoldTextView tvLoginNow;
    RegisterPresenter iRegisterPresenter;
    String[] professionArray;
    String profession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        iRegisterPresenter = new RegisterPresenter(this);
        professionArray = getResources().getStringArray(R.array.profession);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, professionArray);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfession.setPrompt("Select");
        spinnerProfession.setAdapter(spinnerArrayAdapter);
        spinnerProfession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvProfession.setText(professionArray[position]);
                profession = tvProfession.getText().toString();
                if (tvProfession.getText().toString().equalsIgnoreCase("Others")){
                    tvOtherProfession.setVisibility(View.VISIBLE);
                }else {
                    tvOtherProfession.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tvProfession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerProfession.performClick();
            }
        });

    }

    @OnClick({R.id.btnSignUp, R.id.tvLoginNow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                if (validateAll()){
                    iRegisterPresenter.attemptRegister(tvFullName.getText().toString(),tvEmail.getText().toString(),tvPassword.getText().toString(),tvMobile.getText().toString(),profession);
                }
                break;
            case R.id.tvLoginNow:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
        }
    }

    private boolean validateAll() {
        boolean valid = true;
        if (TextUtils.isEmpty(tvFullName.getText().toString())){
            AppHelper.makeToast(RegisterActivity.this,"Please enter full name.");
            valid = false;
        }else if (TextUtils.isEmpty(tvEmail.getText().toString())){
            AppHelper.makeToast(RegisterActivity.this,"Please enter email.");
            valid = false;
        }else if (!AppHelper.isValidEmail(tvEmail.getText().toString())){
            AppHelper.makeToast(RegisterActivity.this,"Please enter a valid email.");
            valid = false;
        }else if (TextUtils.isEmpty(tvPassword.getText().toString())){
            AppHelper.makeToast(RegisterActivity.this,"Please enter password.");
            valid = false;
        }else if (TextUtils.isEmpty(tvConfirmPassword.getText().toString())){
            AppHelper.makeToast(RegisterActivity.this,"Please enter confirm password.");
            valid = false;
        }else if (!tvPassword.getText().toString().equals(tvConfirmPassword.getText().toString())){
            AppHelper.makeToast(RegisterActivity.this,"Both password must be same.");
            valid = false;
        }else if (TextUtils.isEmpty(tvMobile.getText().toString())){
            AppHelper.makeToast(RegisterActivity.this,"Please enter a mobile number.");
            valid = false;
        }else if (TextUtils.isEmpty(tvProfession.getText().toString())){
            AppHelper.makeToast(RegisterActivity.this,"Please enter profession.");
            valid = false;
        }
        return valid;
    }

    @Override
    public void onSuccessRegister(RegisterResponse registerResponse) {
        if (registerResponse != null && (registerResponse.getResult().equalsIgnoreCase("1"))){
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            AppHelper.makeToast(RegisterActivity.this,"You are successfully registered. Thank you!");
        }else {
            startActivity(new Intent(RegisterActivity.this,RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            AppHelper.makeToast(RegisterActivity.this,registerResponse.getMsg());
        }

    }

    @Override
    public void onRegisterFail(String msg) {
        startActivity(new Intent(RegisterActivity.this,RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        AppHelper.makeToast(RegisterActivity.this,msg);
    }
}
