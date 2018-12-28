package com.example.teqani.base.presentation.screens.SignInScreen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teqani.base.R;
import com.example.teqani.base.presentation.BaseFragment;
import com.example.teqani.base.presentation.screens.HomeScreen.MainActivity;
import com.example.teqani.base.presentation.screens.RegisterScreen.RegisterActivity;
import com.example.teqani.base.presentation.screens.VerificationScreen.VerificationActivity;
import com.wang.avi.AVLoadingIndicatorView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class SignInFragment extends BaseFragment {

    @BindView(R.id.register_label)
    TextView register;
    @BindView(R.id.mobile_wrapper)
    TextInputLayout mobileWrapper;
    @BindView(R.id.login_btn)
    TextView login;
    @BindView(R.id.continue_label)
    TextView continueLabel;
    @BindView(R.id.mobile_field)
    EditText mobile;
    @BindView(R.id.progress)
    AVLoadingIndicatorView progress;

    SignInViewModel signInViewModel;

    @Inject
    SignInViewModelFactory signInViewModelFactory;

    public static SignInFragment newInstance() {

        Bundle args = new Bundle();

        SignInFragment fragment = new SignInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);

        signInViewModel = ViewModelProviders.of(this, signInViewModelFactory).get(SignInViewModel.class);

        initLiveObservers();
    }

    private void initLiveObservers() {
        signInViewModel.progressSLD.observe(this, this::showProgress);
        signInViewModel.emptyErrorSLD.observe(this, this::showError);
        signInViewModel.successSLD.observe(this, this::onSuccess);
    }

    private void onSuccess(Boolean aBoolean) {
        Intent intent = new Intent(getContext(), VerificationActivity.class);
        startActivity(intent);
    }

    private void showProgress(Boolean show) {
        if (show) {
            progress.setVisibility(View.VISIBLE);
        } else {
            progress.setVisibility(View.GONE);
        }
    }

    private void showError(Boolean aBoolean) {
        Toast.makeText(getContext(), R.string.phone_field_empty, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        register.setOnClickListener((View v) -> {
            Intent i = new Intent(getContext(), RegisterActivity.class);
            startActivity(i);
        });

        login.setOnClickListener((View v) -> {
            signInViewModel.login(mobile.getText().toString());
        });

        continueLabel.setOnClickListener((View v) -> {
            Intent i = new Intent(getContext(), MainActivity.class);
            startActivity(i);
        });
    }
}
