package com.example.mjhwa.elecston.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mjhwa.elecston.R;

public class SignActivity extends AppCompatActivity {

    SharedPreferences pref; // SharedPreferences는 앱 삭제 전까지는 데이터 저장 가능
    SharedPreferences.Editor editor;

    ImageButton profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        Toast.makeText(getApplicationContext(), "이미지를 클릭하면 앱이 시작됩니다.", Toast.LENGTH_LONG).show();

        profile = (ImageButton) findViewById(R.id.profile);

        pref = getSharedPreferences("User", MODE_PRIVATE);
        editor = pref.edit();

        editor.putString("id","Guest");
        editor.apply();

        profile = (ImageButton) findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                intent.putExtra("id", pref.getString("id", ""));
                startActivity(intent);
            }
        });
    }

}


