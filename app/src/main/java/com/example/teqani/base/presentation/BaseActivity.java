package com.example.teqani.base.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.teqani.base.R;

abstract public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        if (this.getSupportFragmentManager().findFragmentById(R.id.main_content) == null) {
            BaseFragment f = getFragment();
            this.getSupportFragmentManager().beginTransaction().replace(R.id.main_content, (Fragment) f).addToBackStack(f.getClass().getSimpleName()).commit();
        }
    }

    public abstract BaseFragment getFragment();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
