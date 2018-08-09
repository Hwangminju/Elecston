package com.example.mjhwa.elecston.views;

import android.graphics.Typeface;
import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mjhwa.elecston.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.view.View.OnClickListener;
import com.example.mjhwa.elecston.models.CustomGauge;

public class MainActivity extends AppCompatActivity {

    private EditText etPercent;
    private ClipDrawable mImageDrawable;

    // a field in your class
    private int mLevel = 0;
    private int fromLevel = 0;
    private int toLevel = 0;

    private int iLevel = 0;
    private Date mDate;

    public static final int MAX_LEVEL = 10000;
    public static final int LEVEL_DIFF = 100;
    public static final int DELAY = 30;

    public final String text = "Text";

    private CustomGauge gauge;

    private Handler mUpHandler = new Handler();
    private Runnable animateUpImage = new Runnable() {

        @Override
        public void run() {
            doTheUpAnimation(fromLevel, toLevel);
        }
    };

    TextView tvUser;

    private Handler mDownHandler = new Handler();
    private Runnable animateDownImage = new Runnable() {

        @Override
        public void run() {
            doTheDownAnimation(fromLevel, toLevel);
        }
    };

    ImageView imageView;
    ImageButton btPrev;
    Button btToday;
    ImageButton btNext;

    TextView tvDate;

    private String dTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.elec_image);
        imageView.setImageResource(R.drawable.powerpoles);

        tvUser = (TextView) findViewById(R.id.tvUser);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "NanumGothicCoding-Regular.ttf");
        tvUser.setTypeface(typeface);

        String user = getIntent().getStringExtra("user");
        tvUser.setText(user);

        btPrev = (ImageButton) findViewById(R.id.btPrev);
        btToday = (Button) findViewById(R.id.btToday);
        btNext = (ImageButton) findViewById(R.id.btNext);

        btPrev.setEnabled(false);
        btNext.setEnabled(false);

        tvDate = (TextView) findViewById(R.id.tvDate);

        gauge = findViewById(R.id.gauge);

        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        Date currentTime = new Date( );
        dTime = formatter.format ( currentTime );

        ImageView img = (ImageView) findViewById(R.id.imageView1);
        mImageDrawable = (ClipDrawable) img.getDrawable();
        mImageDrawable.setLevel(0);
    }

    private void doTheUpAnimation(int fromLevel, int toLevel) {
        mLevel += LEVEL_DIFF;
        mImageDrawable.setLevel(mLevel);
        if (mLevel <= toLevel) {
            mUpHandler.postDelayed(animateUpImage, DELAY);
        } else {
            mUpHandler.removeCallbacks(animateUpImage);
            MainActivity.this.fromLevel = toLevel;
        }
    }

    private void doTheDownAnimation(int fromLevel, int toLevel) {
        mLevel -= LEVEL_DIFF;
        mImageDrawable.setLevel(mLevel);
        if (mLevel >= toLevel) {
            mDownHandler.postDelayed(animateDownImage, DELAY);
        } else {
            mDownHandler.removeCallbacks(animateDownImage);
            MainActivity.this.fromLevel = toLevel;
        }
    }

    public void onClickToday(View v) {

        // int temp_level = ((Integer.parseInt(etPercent.getText().toString())) * MAX_LEVEL) / 100;

        Spannable spanText = Spannable.Factory.getInstance().newSpannable(dTime);
        spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 0,9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDate.setText(dTime);
        mDate = new Date();
        btPrev.setEnabled(true);
        btNext.setEnabled(false);

        int temp_level = (75 * MAX_LEVEL) / 100;

        if (toLevel == temp_level || temp_level > MAX_LEVEL) {
            return;
        }
        toLevel = (temp_level <= MAX_LEVEL) ? temp_level : toLevel;
        if (toLevel > fromLevel) {
            // cancel previous process first
            mDownHandler.removeCallbacks(animateDownImage);
            MainActivity.this.fromLevel = toLevel;

            mUpHandler.post(animateUpImage);
        } else {
            // cancel previous process first
            mUpHandler.removeCallbacks(animateUpImage);
            MainActivity.this.fromLevel = toLevel;

            mDownHandler.post(animateDownImage);
        }

        new Thread() {
            public void run() {
                final int i = 35;
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gauge.setValue(i);
                            iLevel = i;
                        }
                    });
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    public void onClickPrev(View v) {

        // int temp_level = ((Integer.parseInt(etPercent.getText().toString())) * MAX_LEVEL) / 100;

        int temp_level = toLevel - Integer.parseInt(String.valueOf(Math.round(Math.random() * 5)));

        btToday.setText("TODAY");
        btNext.setEnabled(true);

        if (toLevel == temp_level || temp_level > MAX_LEVEL) {
            return;
        }
        toLevel = (temp_level <= MAX_LEVEL) ? temp_level : toLevel;
        if (toLevel > fromLevel) {
            // cancel previous process first
            mDownHandler.removeCallbacks(animateDownImage);
            MainActivity.this.fromLevel = toLevel;

            mUpHandler.post(animateUpImage);
        } else {
            // cancel previous process first
            mUpHandler.removeCallbacks(animateUpImage);
            MainActivity.this.fromLevel = toLevel;

            mDownHandler.post(animateDownImage);
        }

        new Thread() {
            public void run() {
                final int i = iLevel - Integer.parseInt(String.valueOf(Math.round(Math.random() * 3)));
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gauge.setValue(i);
                            iLevel = i;
                        }
                    });
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        mDate.setDate(mDate.getDate()-1);
        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        dTime = formatter.format ( mDate );

        Spannable spanText = Spannable.Factory.getInstance().newSpannable(dTime);
        spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 0,9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDate.setText(dTime);
    }

    public void onClickNext(View v) {

        // int temp_level = ((Integer.parseInt(etPercent.getText().toString())) * MAX_LEVEL) / 100;

        int temp_level = toLevel + Integer.parseInt(String.valueOf(Math.round(Math.random() * 5)));

        btToday.setText("TODAY");
        btNext.setEnabled(true);

        if (toLevel == temp_level || temp_level > MAX_LEVEL) {
            return;
        }
        toLevel = (temp_level <= MAX_LEVEL) ? temp_level : toLevel;
        if (toLevel > fromLevel) {
            // cancel previous process first
            mDownHandler.removeCallbacks(animateDownImage);
            MainActivity.this.fromLevel = toLevel;

            mUpHandler.post(animateUpImage);
        } else {
            // cancel previous process first
            mUpHandler.removeCallbacks(animateUpImage);
            MainActivity.this.fromLevel = toLevel;

            mDownHandler.post(animateDownImage);
        }

        new Thread() {
            public void run() {
                final int i = iLevel + Integer.parseInt(String.valueOf(Math.round(Math.random() * 3)));
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gauge.setValue(i);
                            iLevel = i;
                        }
                    });
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        mDate.setDate(mDate.getDate()+1);
        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        dTime = formatter.format ( mDate );

        Spannable spanText = Spannable.Factory.getInstance().newSpannable(dTime);
        spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 0,9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDate.setText(dTime);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
