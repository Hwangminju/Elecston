package com.example.mjhwa.elecston.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.mjhwa.elecston.R;

public class SellActivity extends AppCompatActivity {

    ImageButton btDown, btUp;
    ImageButton btDown_2, btUp_2;
    EditText etSell, etSell_2;
    Button btOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        btDown = (ImageButton) findViewById(R.id.btDown);
        btUp = (ImageButton) findViewById(R.id.btUp);

        btDown_2 = (ImageButton) findViewById(R.id.btDown_2);
        btUp_2 = (ImageButton) findViewById(R.id.btUp_2);

        etSell = (EditText) findViewById(R.id.etSell);
        etSell_2 = (EditText) findViewById(R.id.etSell_2);

        btOK = (Button) findViewById(R.id.btOK);

        btDown.setOnClickListener(btnClickListener);
        btUp.setOnClickListener(btnClickListener);
        btDown_2.setOnClickListener(btnClickListener);
        btUp_2.setOnClickListener(btnClickListener);
        btOK.setOnClickListener(btnClickListener);

        // int min = Integer.parseInt("" + etSell.getText());
    }

    private Button.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int temp = Integer.parseInt("" + etSell.getText());
            int temp_2 = Integer.parseInt("" + etSell_2.getText());

            switch (view.getId()) {
                case R.id.btDown:
                    temp = Integer.parseInt("" + etSell.getText());
                    if (temp - 1 < 261) {
                        btDown.setEnabled(false);
                    }

                    else {
                        temp = temp - 1;
                        etSell.setText("" + temp);
                    }
                    break;

                case R.id.btUp:
                    temp = Integer.parseInt("" + etSell.getText());
                    if (temp + 5 >319) {
                        btUp.setEnabled(false);
                    }

                    else {
                        temp = temp + 5;
                        etSell.setText("" + temp);
                    }
                    break;

                case R.id.btDown_2:
                    temp_2 = Integer.parseInt("" + etSell_2.getText());
                    if (temp_2 - 1 < 0) {
                        btDown_2.setEnabled(false);
                    }

                    else {
                        temp_2 = temp_2 - 1;
                        etSell_2.setText("" + temp_2);
                    }
                    break;

                case R.id.btUp_2:
                    temp_2 = Integer.parseInt("" + etSell_2.getText());
                    if (temp_2 + 5 > 28) {
                        btUp_2.setEnabled(false);
                    }

                    else {
                        temp_2 = temp_2 + 5;
                        etSell_2.setText("" + temp_2);
                    }
                    break;

                case R.id.btOK:
                    Intent intent = getIntent();
                    intent.putExtra("price", temp);
                    intent.putExtra("elec", temp_2);
                    setResult(RESULT_OK, intent);
                    finish();
                    break;

                default:
                    break;
            }

        }
    };
}