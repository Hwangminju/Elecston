package com.example.mjhwa.elecston.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mjhwa.elecston.R;

public class CheckActivity extends AppCompatActivity {

    TextView tvBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        tvBuy = (TextView) findViewById(R.id.tvBuy);

        int val = ((SunActivity)SunActivity.context).sun;
        tvBuy.setText("태양광 구매량 :\n" + val + "KWh");
    }
}
