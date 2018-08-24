package com.example.mjhwa.elecston.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mjhwa.elecston.R;

public class Sell2Activity extends AppCompatActivity {

    TextView sell2_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell2);

        sell2_2 = (TextView) findViewById(R.id.sell2_2);

    }
}
