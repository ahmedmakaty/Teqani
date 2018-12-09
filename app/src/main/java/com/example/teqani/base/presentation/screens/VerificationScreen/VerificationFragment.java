package com.example.teqani.base.presentation.screens.VerificationScreen;

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
import com.example.teqani.base.presentation.BaseFragment;
import com.example.teqani.base.presentation.screens.HomeScreen.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    public static VerificationFragment newInstance() {

        Bundle args = new Bundle();

        VerificationFragment fragment = new VerificationFragment();
        fragment.setArguments(args);
        return fragment;
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
                if (count > 0)
                    Toast.makeText(getContext(), "Got it!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        verify.setOnClickListener((View v) -> {
            Intent i = new Intent(getContext(), MainActivity.class);
            startActivity(i);
        });
    }
}
