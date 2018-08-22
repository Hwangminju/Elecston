package com.example.mjhwa.elecston.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mjhwa.elecston.R;

public class AIActivity extends AppCompatActivity {

    TextView ai_1, ai_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai);

        ai_1 = (TextView) findViewById(R.id.ai_1);
        ai_2 = (TextView) findViewById(R.id.ai_2);

        ai_1.setText(R.string.ai_1);
        ai_2.setText(R.string.ai_2);
    }
}
