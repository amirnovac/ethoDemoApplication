package com.amir.ethodemoapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.amir.ethodemoapplication.R;
import com.amir.ethodemoapplication.core.BaseActivity;
import com.amir.ethodemoapplication.databinding.ActivityLoginBinding;
import com.amir.ethodemoapplication.interfaces.AuthListener;
import com.amir.ethodemoapplication.viewmodel.LoginViewModel;

public class Login extends BaseActivity implements View.OnClickListener, AuthListener {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loader = (ConstraintLayout) binding.loader.getRoot();

        implementListener();
        setViewModel();
    }

    /**
     * method to set the login view model
     */
    private void setViewModel() {
        LoginViewModel loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        binding.setLifecycleOwner(this);
        loginViewModel.setAuthListner(this);
        loginViewModel.setContext(mContext);
    }

    @Override
    public void onClick(View v) {
        if (v == binding.tvCreateAccount) {
            startActivity(new Intent(mContext, SignUp.class));
        }
    }


    /**
     * implement on click listeners
     */
    private void implementListener() {
        binding.tvCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onStarting() {
        showLoader();
        hideKeyboard();
    }

    @Override
    public void onSuccess() {
        hideLoader();
        sp.setBoolean(ISLOGIN, true);
        startActivity(new Intent(mContext, Dashboard.class));
        finish();
    }

    @Override
    public void onError(String message) {
        hideLoader();
        showToast(message);
    }

    @Override
    public void onValidationError(String message) {
        showToast(message);
    }
}
