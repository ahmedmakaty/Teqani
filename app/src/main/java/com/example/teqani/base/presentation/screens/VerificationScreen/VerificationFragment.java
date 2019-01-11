package com.example.teqani.base.presentation.screens.VerificationScreen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teqani.base.R;
import com.example.teqani.base.data.Constants;
import com.example.teqani.base.presentation.BaseFragment;
import com.example.teqani.base.presentation.screens.HomeScreen.MainActivity;
import com.wang.avi.AVLoadingIndicatorView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class VerificationFragment extends BaseFragment {

    @BindView(R.id.pin_1)
    EditText pin1;
    @BindView(R.id.pin_2)
    EditText pin2;
    @BindView(R.id.pin_3)
    EditText pin3;
    @BindView(R.id.pin_4)
    EditText pin4;
    @BindView(R.id.pin)
    LinearLayout pin;
    @BindView(R.id.verify_btn)
    TextView verify;
    @BindView(R.id.progress)
    AVLoadingIndicatorView progress;

    VerificationViewModel verificationViewModel;

    @Inject
    VerificationViewModelFactory verificationViewModelFactory;

    public static VerificationFragment newInstance(String phone) {

        Bundle args = new Bundle();

        VerificationFragment fragment = new VerificationFragment();
        args.putString(Constants.PHONE_NUMBER, phone);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidSupportInjection.inject(this);

        verificationViewModel = ViewModelProviders.of(this, verificationViewModelFactory).get(VerificationViewModel.class);

        verificationViewModel.setPhoneNumber(getArguments().getString(Constants.PHONE_NUMBER, ""));
        initLiveObservers();
    }

    private void initLiveObservers() {
        verificationViewModel.emptyErrorSLD.observe(this, this::onError);
        verificationViewModel.successSLD.observe(this, this::onSuccess);
        verificationViewModel.progressSLD.observe(this, this::showProgress);
    }

    private void onSuccess(Boolean aBoolean) {
        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i);
    }

    private void showProgress(Boolean show) {
        if (show) {
            progress.setVisibility(View.VISIBLE);
        } else {
            progress.setVisibility(View.GONE);
        }
    }

    private void onError(Boolean aBoolean) {
        Toast.makeText(getContext(), R.string.verify_field_empty, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verification, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pin1.requestFocus();
        pin.setOnClickListener((View v) -> {
                    pin1.getText().clear();
                    pin2.getText().clear();
                    pin3.getText().clear();
                    pin4.getText().clear();
                    pin1.requestFocus();
                }
        );

        pin1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pin2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pin2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pin3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pin3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pin4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pin4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        verify.setOnClickListener((View v) -> {
            verificationViewModel.verify(pin1.getText().toString() + pin2.getText().toString() + pin3.getText().toString() + pin4.getText().toString(), verificationViewModel.getPhoneNumber());
        });
    }
}
