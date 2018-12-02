package com.example.teqani.base.presentation.screens.RegisterScreen;

import com.example.teqani.base.presentation.BaseActivity;
import com.example.teqani.base.presentation.BaseFragment;

public class RegisterActivity extends BaseActivity {
    @Override
    public BaseFragment getFragment() {
        return RegisterFragment.newInstance();
    }
}
