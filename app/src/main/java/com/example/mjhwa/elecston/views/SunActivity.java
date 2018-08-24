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

    public int sun = 119;
    public static Context context;

    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sun);

        Toast.makeText(this, "태양광 보유량은 " + sun + " KW입니다.", Toast.LENGTH_LONG).show();

        ImageView imageView = (ImageView)findViewById(R.id.sun_image);
        imageView.setImageResource(R.drawable.powerpoles);

        sPagerAdapter mPagerAdapter = new sPagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager)findViewById(R.id.vPager);
        mViewPager.setAdapter(mPagerAdapter);

        TabLayout mTab = (TabLayout)findViewById(R.id.sun_tabs);
        mTab.setupWithViewPager(mViewPager);

        context = this;
    }

    public int getSun() {
        return sun;
    }

    public void addSun(int sunValue) {
        sun = sun + sunValue;
    }
}
