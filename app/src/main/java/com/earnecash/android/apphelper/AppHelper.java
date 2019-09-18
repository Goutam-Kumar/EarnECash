package com.earnecash.android.apphelper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.earnecash.android.R;
import com.earnecash.android.appinstance.EarnECash;
import com.earnecash.android.login.LoginActivity;

public class AppHelper {
    public static final String USER_PREF = "user_pref";
    public static final String USER_DATA = "user_data";
    public static final String ISLOGIN = "is_login";

    public static void getLightTheme(Activity mActivity){
        // fix windowLightStatusBar not changed after applyStyle on Android O
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final TypedArray a = mActivity.obtainStyledAttributes(new int[]{android.R.attr.windowLightStatusBar});
            final boolean windowLightStatusBar = a.getBoolean(0, false);
            a.recycle();
            int flag = mActivity.getWindow().getDecorView().getSystemUiVisibility();
            if (windowLightStatusBar) {
                flag |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                flag &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            mActivity.getWindow().getDecorView().setSystemUiVisibility(flag);
        }
    }

    public static void errorBox(Context context, EditText editBox, String msg){
        editBox.setBackgroundResource(R.drawable.inputbox_error);
        editBox.setFocusable(true);
        AppHelper.makeToast(context,msg);
    }

    public static void myTextWatcher(final Context context, final EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.setBackgroundResource(R.drawable.inputbox);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public static void setDialog(Context context, boolean show){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(R.layout.progress);
        Dialog dialog = builder.create();
        if (show)
            if (!dialog.isShowing())
                dialog.show();
        else
            if (dialog.isShowing())
                dialog.dismiss();
    }


    public static void makeToast(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    public static void LoggerE(String Tag,String msg){
        Log.e(Tag,msg);
    }

    public static void LoggerD(String Tag,String msg){
        Log.d(Tag,msg);
    }

    private static SharedPreferences getPrefs(String type) {
        return EarnECash.context.getSharedPreferences(type, Context.MODE_PRIVATE);
    }

    public static void saveString(String prefType, String type, String value) {
        getPrefs(prefType).edit().putString(type, value).apply();
    }

    public static String getString(String prefType, String type) {
        return getPrefs(prefType).getString(type, "");
    }

    public static void saveBoolean(String prefType, String type, boolean value) {
        getPrefs(prefType).edit().putBoolean(type, value).apply();
    }

    public static boolean getBoolean(String prefType, String type) {
        return getPrefs(prefType).getBoolean(type, false);
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
