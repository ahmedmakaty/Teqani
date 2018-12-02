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

public class Page3Fragment extends BaseFragment {
    public static Page3Fragment newInstance() {

        Bundle args = new Bundle();

        Page3Fragment fragment = new Page3Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial_page_3, container, false);

        ButterKnife.bind(this, view);


        return view;
    }
}
