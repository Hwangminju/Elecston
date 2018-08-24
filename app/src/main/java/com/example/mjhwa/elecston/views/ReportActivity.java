package com.example.mjhwa.elecston.views;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mjhwa.elecston.R;
import com.example.mjhwa.elecston.models.CustomGauge;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReportActivity extends Activity {

    ImageButton btPrev;
    Button btToday;
    ImageButton btNext;
    TextView tvDate;

    TextView text1, text2;

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

    private ClipDrawable mImageDrawable;

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

    private String dTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        btPrev = (ImageButton) findViewById(R.id.btPrev);
        btToday = (Button) findViewById(R.id.btToday);
        btNext = (ImageButton) findViewById(R.id.btNext);

        btToday = (Button) findViewById(R.id.btToday);
        btNext = (ImageButton) findViewById(R.id.btNext);

        btToday.setText("");
        btNext.setEnabled(false);

        tvDate = (TextView) findViewById(R.id.tvDate);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);

        gauge = this.findViewById(R.id.gauge);

        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        Date currentTime = new Date( );
        dTime = formatter.format ( currentTime );

        Spannable spanText = Spannable.Factory.getInstance().newSpannable(dTime);
        spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 0,9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDate.setText(dTime);
        mDate = new Date();

        ImageView img = (ImageView) findViewById(R.id.imageView1);
        mImageDrawable = (ClipDrawable) img.getDrawable();
        mImageDrawable.setLevel(0);

        int level_1 = 3 * (currentTime.getDate() - 1); // 첫 번째 수치
        final int level_2 = currentTime.getDate() - 1;

        int temp_level = (level_1 * MAX_LEVEL) / 100;

        if (toLevel == temp_level || temp_level > MAX_LEVEL) {
            return;
        }

        toLevel = (temp_level <= MAX_LEVEL) ? temp_level : toLevel;

        if (toLevel > fromLevel) {
            // cancel previous process first
            mDownHandler.removeCallbacks(animateDownImage);
            this.fromLevel = toLevel;

            mUpHandler.post(animateUpImage);
        } else {
            // cancel previous process first
            mUpHandler.removeCallbacks(animateUpImage);
            this.fromLevel = toLevel;

            mDownHandler.post(animateDownImage);
        }

        new Thread() {
            public void run() {
                final int i = level_2;
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


        btToday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // write your code here
                onClickToday(view);
            }
        });

        btPrev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // write your code here
                onClickPrev(view);
            }
        });

        btNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // write your code here
                onClickNext(view);
            }
        });
    }

    private void doTheUpAnimation(int fromLevel, int toLevel) {
        mLevel += LEVEL_DIFF;
        mImageDrawable.setLevel(mLevel);
        if (mLevel <= toLevel) {
            mUpHandler.postDelayed(animateUpImage, DELAY);
        } else {
            mUpHandler.removeCallbacks(animateUpImage);
            this.fromLevel = toLevel;
        }
    }

    private void doTheDownAnimation(int fromLevel, int toLevel) {
        mLevel -= LEVEL_DIFF;
        mImageDrawable.setLevel(mLevel);
        if (mLevel >= toLevel) {
            mDownHandler.postDelayed(animateDownImage, DELAY);
        } else {
            mDownHandler.removeCallbacks(animateDownImage);
            this.fromLevel = toLevel;
        }
    }

    public void onClickToday(View v) {

        // int temp_level = ((Integer.parseInt(etPercent.getText().toString())) * MAX_LEVEL) / 100;

        Spannable spanText = Spannable.Factory.getInstance().newSpannable(dTime);
        spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 0,9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        Date currentTime = new Date( );
        mDate = currentTime;
        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        dTime = formatter.format ( currentTime );
        tvDate.setText(dTime);

        btNext.setEnabled(false);

        btToday.setText("");
        btPrev.setEnabled(true);

        int level_1 = 3 * (currentTime.getDate() - 1); // 첫 번째 수치
        final int level_2 = currentTime.getDate() - 1; // 두 번째 수치

        int temp_level = (level_1 * MAX_LEVEL) / 100;

        if (toLevel == temp_level || temp_level > MAX_LEVEL) {
            return;
        }
        toLevel = (temp_level <= MAX_LEVEL) ? temp_level : toLevel;
        if (toLevel > fromLevel) {
            // cancel previous process first
            mDownHandler.removeCallbacks(animateDownImage);
            this.fromLevel = toLevel;

            mUpHandler.post(animateUpImage);
        } else {
            // cancel previous process first
            mUpHandler.removeCallbacks(animateUpImage);
            this.fromLevel = toLevel;

            mDownHandler.post(animateDownImage);
        }

        new Thread() {
            public void run() {
                final int i = level_2;
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

        // 날짜의 일마다 정해지게 수치 정하는 것
        mDate.setDate(mDate.getDate() - 1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(mDate);
        cal.add(Calendar.DATE,0);

        int level_1 = 3 * (Integer.valueOf(cal.get(Calendar.DATE)) - 1);
        final int level_2 = Integer.valueOf(cal.get(Calendar.DATE)) - 1;

        int temp_level = (level_1 * MAX_LEVEL) / 100;

        btToday.setText("TODAY");
        btNext.setEnabled(true);

        if (toLevel == temp_level || temp_level > MAX_LEVEL) {
            return;
        }
        toLevel = (temp_level <= MAX_LEVEL) ? temp_level : toLevel;
        if (toLevel > fromLevel) {
            // cancel previous process first
            mDownHandler.removeCallbacks(animateDownImage);
            this.fromLevel = toLevel;

            mUpHandler.post(animateUpImage);
        } else {
            // cancel previous process first
            mUpHandler.removeCallbacks(animateUpImage);
            this.fromLevel = toLevel;

            mDownHandler.post(animateDownImage);
        }

        new Thread() {
            public void run() {
                final int i = level_2;
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

        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        dTime = formatter.format ( mDate );

        Spannable spanText = Spannable.Factory.getInstance().newSpannable(dTime);
        spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 0,9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDate.setText(dTime);
    }

    public void onClickNext(View v) {

        // int temp_level = ((Integer.parseInt(etPercent.getText().toString())) * MAX_LEVEL) / 100;

        // 날짜의 일마다 정해지게 수치 정하는 것
        mDate.setDate(mDate.getDate() + 1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(mDate);
        cal.add(Calendar.DATE, 0);

        int level_1 = 3 * (Integer.valueOf(cal.get(Calendar.DATE)) - 1);
        final int level_2 = Integer.valueOf(cal.get(Calendar.DATE)) - 1;

        int temp_level = (level_1 * MAX_LEVEL) / 100;

        btToday.setText("TODAY");
        btNext.setEnabled(true);

        if (toLevel == temp_level || temp_level > MAX_LEVEL) {
            return;
        }
        toLevel = (temp_level <= MAX_LEVEL) ? temp_level : toLevel;
        if (toLevel > fromLevel) {
            // cancel previous process first
            mDownHandler.removeCallbacks(animateDownImage);
            this.fromLevel = toLevel;

            mUpHandler.post(animateUpImage);
        } else {
            // cancel previous process first
            mUpHandler.removeCallbacks(animateUpImage);
            this.fromLevel = toLevel;

            mDownHandler.post(animateDownImage);
        }

        new Thread() {
            public void run() {
                final int i = level_2;
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

        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH)+1);
        String date = String.valueOf(cal.get(Calendar.DATE));

        if (month.length() < 2)
            month = "0" + month;
        if (date.length() < 2)
            date = "0" + date;

        if (mDate.getYear() == new Date().getYear() && mDate.getMonth() == new Date().getMonth() && new Date().getDate() == new Date().getDate()) {
            btNext.setEnabled(false);
        }

        if ((cal.get(Calendar.YEAR) == new Date().getYear()) &&
                (cal.get(Calendar.MONTH) == new Date().getMonth()) &&
                        (cal.get(Calendar.DATE) == new Date().getDate())) {
            btNext.setEnabled(false);
        }

        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        dTime = formatter.format ( mDate );

        Spannable spanText = Spannable.Factory.getInstance().newSpannable(dTime);
        spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 0,9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDate.setText(dTime);

        // tvDate.setText(year + "-" + month + "-" + date);
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);
    }
}
