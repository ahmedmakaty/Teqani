package com.example.teqani.base.presentation.screens.SignInScreen;

import com.example.teqani.base.presentation.BaseActivity;
import com.example.teqani.base.presentation.BaseFragment;

public class SignInActivity extends BaseActivity {
    @Override
    public BaseFragment getFragment() {
        return SignInFragment.newInstance();
    }
}
