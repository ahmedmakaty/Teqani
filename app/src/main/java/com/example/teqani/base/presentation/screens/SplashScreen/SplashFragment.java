package com.example.teqani.base.presentation.screens.SplashScreen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teqani.base.R;
import com.example.teqani.base.presentation.BaseFragment;
import com.example.teqani.base.presentation.screens.TutorialScreen.TutorialActivity;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class SplashFragment extends BaseFragment {

    Toolbar toolbar;
    @Inject
    SplashViewModelFactory splashViewModelFactory;
    SplashViewModel splashViewModel;
    private AlertDialog mProgress;

    public static SplashFragment newInstance() {

        return new SplashFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        //initializeToolbar();

        initClickListeners();

        //setupRecyclerView();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getContext(), TutorialActivity.class);
                startActivity(intent);
            }
        }, 2000);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        splashViewModel = ViewModelProviders.of(this, splashViewModelFactory).get(SplashViewModel.class);

        initiateLiveObservers();

    }

    private void initiateLiveObservers() {

    }

    private void showErrorMessage(String s) {
        Snackbar.make(getView(), s, Snackbar.LENGTH_LONG);
    }

    public void initializeToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.main_title));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void initClickListeners() {

//        addNewCard.setOnClickListener((View v) -> {
//            goToPayfortScreen();
//        });

    }

    private void showProgress(Boolean show) {
        if (mProgress != null) {
            if (show) {
                mProgress.show();
            } else {
                mProgress.dismiss();
            }
        }
    }

    private void onBackPressed() {
        getActivity().finish();
    }

}
