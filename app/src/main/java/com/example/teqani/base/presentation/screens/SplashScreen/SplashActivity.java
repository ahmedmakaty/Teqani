package com.example.teqani.base.presentation.screens.SplashScreen;

import com.example.teqani.base.presentation.BaseActivity;
import com.example.teqani.base.presentation.BaseFragment;

public class SplashActivity extends BaseActivity {

    @Override
    public BaseFragment getFragment() {
        return SplashFragment.newInstance();
    }
}
