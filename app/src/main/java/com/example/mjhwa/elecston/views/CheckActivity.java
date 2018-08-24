package com.example.mjhwa.elecston.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mjhwa.elecston.R;
import com.example.mjhwa.elecston.SunFragments.MyFragment;

public class CheckActivity extends AppCompatActivity {

    TextView tvBuy, tvSell;
    int val = 0;
    int val2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        tvBuy = (TextView) findViewById(R.id.tvBuy);
        tvSell = (TextView) findViewById(R.id.tvSell);

        if ((SunActivity)SunActivity.context != null) {
            val = ((SunActivity) SunActivity.context).sun;
            tvBuy.setText("태양광 구매 :\n" + val + "KWh");

            val2 = ((SunActivity)SunActivity.context).sum;
            tvSell.setText("태양광 판매등록 :\n" + val2 + "KWh");
        }
        else {
            tvBuy.setText("태양광 구매 :\n" + "0" + "KWh");
            tvSell.setText("태양광 판매등록 :\n" + "0" + "KWh");
        }




    }
}
