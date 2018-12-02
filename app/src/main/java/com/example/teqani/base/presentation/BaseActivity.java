package com.example.teqani.base.presentation;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.teqani.base.R;
import com.example.teqani.base.helper.LocaleHelper;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

abstract public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        if (this.getSupportFragmentManager().findFragmentById(R.id.main_content) == null) {
            BaseFragment f = getFragment();
            this.getSupportFragmentManager().beginTransaction().replace(R.id.main_content, (Fragment) f).addToBackStack(f.getClass().getSimpleName()).commit();
        }

        LocaleHelper l = LocaleHelper.getInstance(getBaseContext());
        String country = getString(R.string.default_Country);

        l.setLocale(getBaseContext(), country.toUpperCase(), l.getLanguage());

        if (l.getLanguage().equals("ar")) {
            LocaleHelper.ChangeDesignToRTL(this);
        } else {
            LocaleHelper.ChangeDesignToLTR(this);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        fixAlignment();
    }

    public void fixAlignment() {
        LocaleHelper l = LocaleHelper.getInstance(getBaseContext());
        String country = getString(R.string.default_Country);
        l.setLocale(getBaseContext(), country.toUpperCase(), l.getLanguage());
    }

    @Override
    protected void attachBaseContext(Context newBase) {

        //Set Application local

        LocaleHelper l = LocaleHelper.getInstance(newBase);

        String country = newBase.getString(R.string.default_Country);

        newBase = l.setLocale(newBase, country.toUpperCase(), l
                .getLanguage());

        super.attachBaseContext(newBase);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocaleHelper l = LocaleHelper.getInstance(getBaseContext());
        String country = getString(R.string.default_Country);
        l.setLocale(getBaseContext(), country.toUpperCase(), l.getLanguage());
    }

    public abstract BaseFragment getFragment();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
