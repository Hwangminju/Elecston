package com.example.mjhwa.elecston.views;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mjhwa.elecston.Adapters.PagerAdapter;
import com.example.mjhwa.elecston.R;
import com.example.mjhwa.elecston.TabFragments.Tab1Fragment;
import com.example.mjhwa.elecston.TabFragments.Tab2Fragment;
import com.example.mjhwa.elecston.TabFragments.Tab3Fragment;

public class TranActivity extends AppCompatActivity implements
        Tab1Fragment.OnFragmentInteractionListener,
        Tab2Fragment.OnFragmentInteractionListener,
        Tab3Fragment.OnFragmentInteractionListener {

    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tran);

        ImageView imageView = (ImageView)findViewById(R.id.elec_image);
        imageView.setImageResource(R.drawable.powerpoles);

        PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager)findViewById(R.id.viewpager);
        mViewPager.setAdapter(mPagerAdapter);

        TabLayout mTab = (TabLayout)findViewById(R.id.tabs);
        mTab.setupWithViewPager(mViewPager);
    }

}
