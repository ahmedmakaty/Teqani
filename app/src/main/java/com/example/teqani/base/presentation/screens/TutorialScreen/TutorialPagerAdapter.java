package com.example.teqani.base.presentation.screens.TutorialScreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.teqani.base.presentation.screens.TutorialScreen.TutorialPages.Page1Fragment;
import com.example.teqani.base.presentation.screens.TutorialScreen.TutorialPages.Page2Fragment;
import com.example.teqani.base.presentation.screens.TutorialScreen.TutorialPages.Page3Fragment;

public class TutorialPagerAdapter extends FragmentStatePagerAdapter {
    public TutorialPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Page1Fragment.newInstance();
            case 1:
                return Page2Fragment.newInstance();
            case 2:
                return Page3Fragment.newInstance();
            default:
                return Page1Fragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
