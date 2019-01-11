package com.example.teqani.base.presentation.screens.VerificationScreen;

import com.example.teqani.base.data.Constants;
import com.example.teqani.base.presentation.BaseActivity;
import com.example.teqani.base.presentation.BaseFragment;

public class VerificationActivity extends BaseActivity {
    @Override
    public BaseFragment getFragment() {
        return VerificationFragment.newInstance(getIntent().getExtras().getString(Constants.PHONE_NUMBER));
    }
}
