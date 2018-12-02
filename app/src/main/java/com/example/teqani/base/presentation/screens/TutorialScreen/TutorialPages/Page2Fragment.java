package com.example.teqani.base.presentation.screens.TutorialScreen.TutorialPages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.teqani.base.R;
import com.example.teqani.base.presentation.BaseFragment;
import com.example.teqani.base.presentation.screens.SignInScreen.SignInActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Page2Fragment extends BaseFragment {

    @BindView(R.id.skip)
    TextView skip;
    public static Page2Fragment newInstance() {

        Bundle args = new Bundle();

        Page2Fragment fragment = new Page2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial_page_2, container, false);

        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        skip.setOnClickListener((View v) -> {
            Intent intent = new Intent(getContext(), SignInActivity.class);
            startActivity(intent);
        });
    }
}
