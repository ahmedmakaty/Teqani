package com.example.teqani.base.presentation.screens.SignInScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.teqani.base.R;
import com.example.teqani.base.presentation.BaseFragment;
import com.example.teqani.base.presentation.screens.RegisterScreen.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInFragment extends BaseFragment {

    @BindView(R.id.register_label)
    TextView register;
    @BindView(R.id.mobile_wrapper)
    TextInputLayout mobileWrapper;
    @BindView(R.id.login_btn)
    TextView login;
    @BindView(R.id.continue_label)
    TextView continueLabel;

    public static SignInFragment newInstance() {

        Bundle args = new Bundle();

        SignInFragment fragment = new SignInFragment();
        fragment.setArguments(args);
        return fragment;
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
            Intent intent = new Intent(getContext(), RegisterActivity.class);
            startActivity(intent);
        });
    }
}
