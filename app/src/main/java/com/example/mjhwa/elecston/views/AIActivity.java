package com.example.mjhwa.elecston.views;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mjhwa.elecston.R;
import com.tomer.fadingtextview.FadingTextView;

public class AIActivity extends AppCompatActivity {

    TextView ai_1, ai_3, ai_4;
    FadingTextView ai_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai);

        ai_1 = (TextView) findViewById(R.id.ai_1);
        ai_3 = (TextView) findViewById(R.id.ai_3);
        ai_4 = (TextView) findViewById(R.id.ai_4);

        ai_2 = (FadingTextView) findViewById(R.id.ai_2);

        ai_2.setTimeout(FadingTextView.SECONDS,4);

    }
}
