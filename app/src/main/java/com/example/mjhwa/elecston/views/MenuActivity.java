package com.example.mjhwa.elecston.views;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mjhwa.elecston.R;

public class MenuActivity extends AppCompatActivity {

    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;

    ListView lvDrawerList;
    ArrayAdapter<String> adtDrawerList;
    String[] menuItems = new String[]{"HOME", "AI 집사의 추천", "누진세 거래소", "태양광 거래소", "나만의 체크리스트", "KEPCO Trust 약관", "FAQ"};

    Fragment fragHome;
    Fragment fragAI;
    Fragment fragTran1;
    Fragment fragTran2;
    Fragment fragMyList;
    Fragment fragTrust;
    Fragment fragFAQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        fragHome = new Fragment();
        fragAI = new Fragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_menu, fragHome).commit();

        lvDrawerList = (ListView) findViewById(R.id.lv_activity_menu);
        adtDrawerList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItems);
        lvDrawerList.setAdapter(adtDrawerList);
        lvDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_menu, fragHome);
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_menu, fragAI);
                        break;
                    case 2:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
                dlDrawer.closeDrawer(lvDrawerList);
            }
        });

        dlDrawer = (DrawerLayout) findViewById(R.id.dl_activity_menu);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.drawable.ic_launcher_background, R.string.app_name);
    }
}
