package com.boyma.mxsmpl.net.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    final static String FILE_NAME = "preferences";

    final static String param = "myparam";
    final static String tokenkey = "token";
    final static String emailkey = "email";
    final static String passkey = "pass";

    private SharedPreferences preferences;

    public Preferences(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, 0);
    }

    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    public void setParam(String data) {
        getEditor().putString(param, data).commit();
    }

    public String getParam() {
        return preferences.getString(param, "");
    }

    public String getToken() {
        return preferences.getString(tokenkey, "");
    }

    public void saveToken(String token) {
        getEditor().putString(tokenkey, token).commit();
    }

    public void saveEmail(String email) {
        getEditor().putString(emailkey, email).commit();
    }

    public String getEmail() {
        return preferences.getString(emailkey, "");
    }

    public void savePass(String password) {
        getEditor().putString(passkey, password).commit();
    }

    public String getPass() {
        return preferences.getString(passkey, "");
    }
}
