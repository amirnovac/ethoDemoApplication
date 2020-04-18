package com.amir.ethodemoapplication.model;

import android.util.Patterns;

public class LoginModel {
    private String mEmail;
    private String mPassword;


    public LoginModel(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public String getEmail() {
        if (mEmail == null) {
            return "";
        }
        return mEmail;
    }


    public String getPassword() {

        if (mPassword == null) {
            return "";
        }
        return mPassword;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }


    public boolean isPasswordLengthGreaterThan5() {
        return getPassword().length() > 5;
    }

}