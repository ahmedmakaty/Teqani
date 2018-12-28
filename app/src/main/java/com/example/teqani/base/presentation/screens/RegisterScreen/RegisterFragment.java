package com.example.teqani.base.presentation.screens.RegisterScreen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teqani.base.R;
import com.example.teqani.base.presentation.BaseFragment;
import com.example.teqani.base.presentation.screens.VerificationScreen.VerificationActivity;
import com.wang.avi.AVLoadingIndicatorView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

public class RegisterFragment extends BaseFragment {

    @BindView(R.id.register_btn)
    TextView registerBtn;
    @BindView(R.id.name_field)
    EditText name;
    @BindView(R.id.mobile_field)
    EditText phone;
    @BindView(R.id.progress)
    AVLoadingIndicatorView progress;

    RegisterViewModel registerViewModel;

    @Inject
    RegisterViewModelFactory registerViewModelFactory;

    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);

        registerViewModel = ViewModelProviders.of(this, registerViewModelFactory).get(RegisterViewModel.class);

        initLiveObservers();
    }

    private void initLiveObservers() {
        registerViewModel.progressSLD.observe(this, this::showProgress);
        registerViewModel.errorSLD.observe(this, this::showError);
        registerViewModel.successSLD.observe(this, this::onSuccess);
    }

    private void onSuccess(Boolean aBoolean) {
        Intent intent = new Intent(getContext(), VerificationActivity.class);
        startActivity(intent);
    }

    private void showError(RegisterViewModel.RegisterError registerError) {
        switch (registerError) {
            case CLEAR:
                break;
            case EMPTY_BOTH:
                Toast.makeText(getContext(), R.string.both_fields_empty, Toast.LENGTH_SHORT).show();
                break;
            case EMPTY_NAME:
                Toast.makeText(getContext(), R.string.name_field_empty, Toast.LENGTH_SHORT).show();
                break;
            case EMPTY_PHONE:
                Toast.makeText(getContext(), R.string.phone_field_empty, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showProgress(Boolean show) {
        if (show) {
            progress.setVisibility(View.VISIBLE);
        } else {
            progress.setVisibility(View.GONE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick(R.id.register_btn)
    public void register() {
        registerViewModel.register(name.getText().toString(), phone.getText().toString());
    }
}
