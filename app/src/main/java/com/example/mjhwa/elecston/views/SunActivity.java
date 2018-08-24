package com.example.mjhwa.elecston.views;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mjhwa.elecston.Adapters.PagerAdapter;
import com.example.mjhwa.elecston.Adapters.sPagerAdapter;
import com.example.mjhwa.elecston.R;
import com.example.mjhwa.elecston.SunFragments.CurrencyFragment;
import com.example.mjhwa.elecston.SunFragments.MyFragment;
import com.example.mjhwa.elecston.SunFragments.YourFragment;
import com.example.mjhwa.elecston.TranFragments.Tab1Fragment;
import com.example.mjhwa.elecston.TranFragments.Tab2Fragment;

public class SunActivity extends AppCompatActivity implements
        CurrencyFragment.OnFragmentInteractionListener,
        YourFragment.OnFragmentInteractionListener,
        MyFragment.OnFragmentInteractionListener {

    // 액티비티 간 데이터 공유
    public int sun = 119;
    public int sum = 0;
    public int mileage = 0;
    public static Context context;

    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sun);


        ImageView imageView = (ImageView)findViewById(R.id.sun_image);
        imageView.setImageResource(R.drawable.powerpoles);

        sPagerAdapter mPagerAdapter = new sPagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager)findViewById(R.id.vPager);
        mViewPager.setAdapter(mPagerAdapter);

        TabLayout mTab = (TabLayout)findViewById(R.id.sun_tabs);
        mTab.setupWithViewPager(mViewPager);

        context = this;

        Toast.makeText(this, "태양광 보유량은 " + ((SunActivity)SunActivity.context).getSun() + " KW입니다.", Toast.LENGTH_LONG).show();

    }

    public int getSun() {
        return ((SunActivity)SunActivity.context).sun;
    } // 구매량

    public void addSun(int sunValue) {
        int val = ((SunActivity)SunActivity.context).sun;
        val = val + sunValue;
        ((SunActivity)SunActivity.context).sun = val;
    }

    public int getSum() {
        return ((SunActivity)SunActivity.context).sum;
    } // 판매량

    public void addSum(int sunValue) {
        int val2 = ((SunActivity)SunActivity.context).sum;
        val2 = val2 + sunValue;
        ((SunActivity)SunActivity.context).sum = val2;
    }

    public int getMileage() {
        return ((SunActivity)SunActivity.context).mileage;
    }

    public void addMileage(int mil) {
        int val3 = ((SunActivity)SunActivity.context).mileage;
        val3 = val3 + mil;
        ((SunActivity)SunActivity.context).mileage = val3;
    }
}
