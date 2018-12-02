package com.example.teqani.base.presentation.screens.TutorialScreen;

import com.example.teqani.base.presentation.BaseActivity;
import com.example.teqani.base.presentation.BaseFragment;

public class TutorialActivity extends BaseActivity {
    @Override
    public BaseFragment getFragment() {
        return TutorialFragment.newInstance();
    }
}
