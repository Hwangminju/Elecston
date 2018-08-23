package com.example.mjhwa.elecston.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mjhwa.elecston.R;

public class SignActivity extends AppCompatActivity {

    EditText idInput, passwordInput;
    Button loginButton, signupButton;
    CheckBox autoLogin;
    Boolean loginChecked = false;
    SharedPreferences pref; // SharedPreferences는 앱 삭제 전까지는 데이터 저장 가능
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        Toast.makeText(getApplicationContext(),"회원가입 : ID와 비밀번호 입력 후에 Sign Up 버튼 클릭",Toast.LENGTH_LONG).show();

        loginButton = (Button) findViewById(R.id.loginButton);
        signupButton = (Button) findViewById(R.id.signupButton);
        idInput = (EditText) findViewById(R.id.idInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        autoLogin = (CheckBox) findViewById(R.id.checkBox);

        pref = getSharedPreferences("User", MODE_PRIVATE);
        editor = pref.edit();

        loadInfo();

        // set checkBoxListener
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    loginChecked = true;
                } else {
                    // if unChecked, removeAll
                    loginChecked = false;
                    editor.clear();
                    editor.commit();
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInfo();
            }
        });

    }

    private void saveInfo() {
        SharedPreferences pref = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("id","" + idInput.getText());
        editor.putString("pw","" + passwordInput.getText());
        if (loginChecked)
            editor.putBoolean("autologin", true);
        else
            editor.putBoolean("autologin", false);
        editor.commit();
    }

    private void loadInfo() {
        SharedPreferences pref = getSharedPreferences("User",MODE_PRIVATE);
        // if autoLogin checked, get input
        if (pref.getBoolean("autoLogin", false)) {
            idInput.setText(pref.getString("id", ""));
            passwordInput.setText(pref.getString("pw", ""));
            autoLogin.setChecked(true);
            final String id = pref.getString("id", "");

            // goto mainActivity
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            });


        } else {
            // if autoLogin unChecked
            final String id = idInput.getText().toString();
            String password = passwordInput.getText().toString();
            Boolean validation = loginValidation(id, password);

            if(validation) {
                Toast.makeText(SignActivity.this, "Login Success", Toast.LENGTH_LONG).show();

                // save id, password to Database

                if(loginChecked) {
                    // if autoLogin Checked, save values
                    editor.putString("id", id);
                    editor.putString("pw", password);
                    editor.putBoolean("autoLogin", true);
                    editor.commit();
                }
                // goto mainActivity
                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplication(), MainActivity.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                });

            } else {
                Toast.makeText(SignActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplication(), SignActivity.class);
                startActivity(intent);
                // goto LoginActivity
            }
        }
    }

    private boolean loginValidation(String id, String password) {
        if(pref.getString("id","").equals(id) && pref.getString("pw","").equals(password)) {
            // login success
            return true;
        } else if (pref.getString("id","").equals(null)){
            // sign in first
            Toast.makeText(SignActivity.this, "Please Sign in first", Toast.LENGTH_LONG).show();
            return false;
        } else {
            // login failed
            return false;
        }
    }

}


