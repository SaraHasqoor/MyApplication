package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

public class PreferencesUtility {

    private static final String PREFS_NAME = "LoginPrefs";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER_ME = "rememberMe";

    public static void saveLoginInfo(Context context, String email, String password, boolean rememberMe) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if (rememberMe) {
            editor.putString(KEY_EMAIL, email);
            editor.putString(KEY_PASSWORD, password);
            editor.putBoolean(KEY_REMEMBER_ME, true);
        } else {
            editor.clear();
        }
        editor.apply();
    }


    public static Map<String, String> getSavedLoginInfo(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put(KEY_EMAIL, prefs.getString(KEY_EMAIL, ""));
        loginInfo.put(KEY_PASSWORD, prefs.getString(KEY_PASSWORD, ""));
        loginInfo.put(KEY_REMEMBER_ME, String.valueOf(prefs.getBoolean(KEY_REMEMBER_ME, false)));
        return loginInfo;
    }
}
