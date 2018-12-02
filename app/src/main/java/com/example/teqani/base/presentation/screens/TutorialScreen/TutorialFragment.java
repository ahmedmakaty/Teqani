package com.example.teqani.base.presentation.screens.TutorialScreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teqani.base.R;
import com.example.teqani.base.presentation.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TutorialFragment extends BaseFragment {

    @BindView(R.id.view_pager)
    ViewPager tutorial;

    TutorialPagerAdapter tutorialPagerAdapter;

    public static TutorialFragment newInstance() {

        Bundle args = new Bundle();

        TutorialFragment fragment = new TutorialFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial, container, false);

        ButterKnife.bind(this, view);

        tutorialPagerAdapter = new TutorialPagerAdapter(getChildFragmentManager());

        tutorial.setAdapter(tutorialPagerAdapter);

        return view;
    }
}
