package com.example.mjhwa.elecston.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mjhwa.elecston.SunFragments.CurrencyFragment;
import com.example.mjhwa.elecston.SunFragments.YourFragment;
import com.example.mjhwa.elecston.SunFragments.MyFragment;

public class sPagerAdapter extends FragmentPagerAdapter {

    private static int PAGE_NUMBER = 3;

    public sPagerAdapter(FragmentManager fm) {
        super (fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return CurrencyFragment.newInstance();
            case 1:
                return YourFragment.newInstance();
            case 2:
                return MyFragment.newInstance();
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
                return "판매";
            default:
                return null;
        }
    }
}
