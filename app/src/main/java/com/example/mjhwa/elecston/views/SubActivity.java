package com.example.mjhwa.elecston.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mjhwa.elecston.CircleAngleAnimation;
import com.example.mjhwa.elecston.R;
import com.example.mjhwa.elecston.models.Circle;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Circle circle = (Circle) findViewById(R.id.circle);

        CircleAngleAnimation animation = new CircleAngleAnimation(circle, 240);
        animation.setDuration(1000);
        circle.startAnimation(animation);
    }
}
