package com.example.teqani.base.presentation.screens.TutorialScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teqani.base.R;
import com.example.teqani.base.presentation.BaseFragment;
import com.example.teqani.base.presentation.screens.SignInScreen.SignInActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TutorialFragment extends BaseFragment {

    @BindView(R.id.view_pager)
    ViewPager tutorial;
    @BindView(R.id.indicator_container)
    LinearLayout indicator;
    @BindView(R.id.skip)
    TextView skip;

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

        tutorial.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicator.removeAllViews();
                for (int i = 0; i < tutorialPagerAdapter.getCount(); i++) {
                    if (i == position) {
                        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                        View view = layoutInflater.inflate(R.layout.oval_selected, indicator);
                    } else {
                        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                        View view = layoutInflater.inflate(R.layout.oval_not_selected, indicator);
                        //indicator.addView(view);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        skip.setOnClickListener((View v) -> {
            Intent intent = new Intent(getContext(), SignInActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
