package com.example.teqani.base.presentation.screens.TutorialScreen.TutorialPages;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teqani.base.R;
import com.example.teqani.base.presentation.BaseFragment;

import butterknife.ButterKnife;

public class Page1Fragment extends BaseFragment {
    public static Page1Fragment newInstance() {

        Bundle args = new Bundle();

        Page1Fragment fragment = new Page1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial_page_1, container, false);

        ButterKnife.bind(this, view);


        return view;
    }
}
