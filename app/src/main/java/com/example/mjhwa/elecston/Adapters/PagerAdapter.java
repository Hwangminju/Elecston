package com.example.mjhwa.elecston.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mjhwa.elecston.TabFragments.Tab1Fragment;
import com.example.mjhwa.elecston.TabFragments.Tab2Fragment;
import com.example.mjhwa.elecston.TabFragments.Tab3Fragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private static int PAGE_NUMBER = 3;

    public PagerAdapter(FragmentManager fm) {
        super (fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Tab1Fragment.newInstance();
            case 1:
                return Tab2Fragment.newInstance();
            case 2:
                return Tab3Fragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "현재 시세";
            case 1:
                return "구매";
            case 2:
                return "나의 목록";
            default:
                return null;
        }
    }
}
