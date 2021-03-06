package com.example.mjhwa.elecston.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mjhwa.elecston.Fragments.AIFragment;
import com.example.mjhwa.elecston.Fragments.CheckFragment;
import com.example.mjhwa.elecston.Fragments.FAQFragment;
import com.example.mjhwa.elecston.Fragments.TranFragment;
import com.example.mjhwa.elecston.Fragments.TrustFragment;
import com.example.mjhwa.elecston.R;

public class MainActivity extends AppCompatActivity
        implements
        AIFragment.OnFragmentInteractionListener,
        TranFragment.OnFragmentInteractionListener,
        CheckFragment.OnFragmentInteractionListener,
        TrustFragment.OnFragmentInteractionListener,
        FAQFragment.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {

    Button btn_ai, btn_tran, btn_sun, btn_check, btn_trust, btn_faq;
    Button btLogOut;
    TextView tvUser;
    Intent intent = getIntent();

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("User", MODE_PRIVATE);

        intent = getIntent();
        // id = intent.getStringExtra("id");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        findViewById(R.id.btn_ai).setOnClickListener(btnClickListener);
        findViewById(R.id.btn_tran).setOnClickListener(btnClickListener);
        findViewById(R.id.btn_sun).setOnClickListener(btnClickListener);
        findViewById(R.id.btn_check).setOnClickListener(btnClickListener);
        findViewById(R.id.btn_trust).setOnClickListener(btnClickListener);
        findViewById(R.id.btn_faq).setOnClickListener(btnClickListener);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        View navHeaderView = navigationView.getHeaderView(0);
        tvUser = (TextView) navHeaderView.findViewById(R.id.tvUser);
        tvUser.setText(pref.getString("id",""));
        Log.d("id 전달", pref.getString("id", ""));

        btLogOut = (Button) navHeaderView.findViewById(R.id.btLogout);
        btLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("User",MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                Intent intent = new Intent(getApplication(), SignActivity.class);
                Toast.makeText(MainActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();

                editor.clear();
                editor.commit();
                finish();

            }
        });
    }

    private Button.OnClickListener btnClickListener = new View.OnClickListener() {

        Fragment fragment = null;
        Class fragmentClass = null;
        Activity activity = null;
        Class activityClass = null;

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_ai:
                    // fragmentClass = AIFragment.class;
                    activityClass = AIActivity.class;
                    break;

                case R.id.btn_tran:
                    // fragmentClass = TranActivity.class;
                    activityClass = TranActivity.class;
                    break;

                case R.id.btn_sun:
                    // fragmentClass = SunFragment.class;
                    activityClass = SunActivity.class;
                    break;

                case R.id.btn_check:
                    // fragmentClass = CheckFragment.class;
                    activityClass = CheckActivity.class;
                    break;

                case R.id.btn_trust:
                    // fragmentClass = TrustFragment.class;
                    activityClass = TrustActivity.class;
                    break;

                case R.id.btn_faq:
                    // fragmentClass = FAQFragment.class;
                    activityClass = FAQActivity.class;
                    break;

                default:
                    activityClass = MainActivity.class;
                    break;

            }

            /*
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            */
            Intent intent = new Intent(getApplicationContext(), activityClass);
            startActivity(intent);
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("InflateParams")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        Activity activity = null;
        Class fragmentClass;
        Class activityClass = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                // fragmentClass = MainActivity.class;
                activityClass = MainActivity.class;
                break;

            case R.id.nav_ai:
                // fragmentClass = AIFragment.class;
                activityClass = AIActivity.class;
                break;

            case R.id.nav_tran:
                // fragmentClass = TranFragment.class;
                activityClass = TranActivity.class;
                break;

            case R.id.nav_sun:
                // fragmentClass = SunFragment.class;
                activityClass = SunActivity.class;
                break;

            case R.id.nav_check:
                // fragmentClass = CheckFragment.class;
                activityClass = CheckActivity.class;
                break;

            case R.id.nav_trust:
                // fragmentClass = TrustFragment.class;
                activityClass = TrustActivity.class;
                break;

            case R.id.nav_faq:
                // fragmentClass = FAQFragment.class;
                activityClass = FAQActivity.class;
                break;

            default:
                activityClass = MainActivity.class;
                break;

        }

        /*
        try {
            // fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        */

        Intent intent = new Intent(this, activityClass);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        item.setCheckable(true);
        setTitle(item.getTitle());
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
