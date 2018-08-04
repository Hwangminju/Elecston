package com.example.mjhwa.elecston;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

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
