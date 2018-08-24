package com.example.mjhwa.elecston.models;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mjhwa.elecston.R;

public class CustomDialog extends Dialog implements View.OnClickListener{
    private static final int LAYOUT = R.layout.dialog_custom;

    private Context context;
    private MyDialogListener dialogListener;

    private TextView okTv;
    private TextView cancelTv;

    private int sun;
    EditText etBuy;

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public CustomDialog(Context context, int sun){
        super(context);
        this.context = context;
        this.sun = sun;
    }

    public void setDialogListener(MyDialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        okTv = (TextView) findViewById(R.id.ok);
        cancelTv = (TextView) findViewById(R.id.cancel);

        okTv.setOnClickListener(this);
        cancelTv.setOnClickListener(this);

        etBuy = (EditText) findViewById(R.id.etBuy);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok:
                int buy = Integer.parseInt("" + etBuy.getText());
                dialogListener.onPositiveClicked(buy);
                dismiss();
                break;
            case R.id.cancel:
                cancel();
                break;
        }
    }
}

