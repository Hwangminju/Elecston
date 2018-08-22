package com.example.mjhwa.elecston.TabFragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mjhwa.elecston.Adapters.PagerAdapter;
import com.example.mjhwa.elecston.Fragments.TranFragment;
import com.example.mjhwa.elecston.R;
import com.example.mjhwa.elecston.models.CustomGauge;
import com.example.mjhwa.elecston.views.ReportActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab1Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button thisMonth;
    ImageButton btPrev;
    Button btToday;
    ImageButton btNext;

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

    TextView tv1, tv2, tv4, tv5, tv6, tv7;

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

    TextView tvDate;

    private String dTime;

    private OnFragmentInteractionListener mListener;

    public Tab1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Tab1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab1Fragment newInstance() {
        Tab1Fragment fragment = new Tab1Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tab1, container, false);

        tv1 = (TextView) v.findViewById(R.id.tv1);
        tv2 = (TextView) v.findViewById(R.id.tv2);
        tv4 = (TextView) v.findViewById(R.id.tv4);
        tv5 = (TextView) v.findViewById(R.id.tv5);
        tv6 = (TextView) v.findViewById(R.id.tv6);
        tv7 = (TextView) v.findViewById(R.id.tv7);

        tv1.setText(R.string.tv1);
        tv2.setText(R.string.tv2);
        tv4.setText(R.string.tv4);
        tv5.setText(R.string.tv5);
        tv6.setText(R.string.tv6);
        tv7.setText(R.string.tv7);

        thisMonth = (Button)v.findViewById(R.id.thisMonth);
        /*btPrev = (ImageButton)v.findViewById(R.id.btPrev);
        btToday = (Button)v.findViewById(R.id.btToday);
        btNext = (ImageButton)v.findViewById(R.id.btNext);

        btPrev.setEnabled(false);
        btNext.setEnabled(false);

        tvDate = (TextView)v.findViewById(R.id.tvDate);*/

        gauge = v.findViewById(R.id.gauge);

        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        Date currentTime = new Date( );
        dTime = formatter.format ( currentTime );

        ImageView img = (ImageView)v.findViewById(R.id.imageView1);
        mImageDrawable = (ClipDrawable) img.getDrawable();
        mImageDrawable.setLevel(0);

        int temp_level = (75 * MAX_LEVEL) / 100;

        if (toLevel == temp_level || temp_level > MAX_LEVEL) {
            return v;
        }

        toLevel = (temp_level <= MAX_LEVEL) ? temp_level : toLevel;

        if (toLevel > fromLevel) {
            // cancel previous process first
            mDownHandler.removeCallbacks(animateDownImage);
            Tab1Fragment.this.fromLevel = toLevel;

            mUpHandler.post(animateUpImage);
        } else {
            // cancel previous process first
            mUpHandler.removeCallbacks(animateUpImage);
            Tab1Fragment.this.fromLevel = toLevel;

            mDownHandler.post(animateDownImage);
        }

        new Thread() {
            public void run() {
                final int i = 30;
                try {
                    activity.runOnUiThread(new Runnable() {
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

        thisMonth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), ReportActivity.class);
                getActivity().startActivity(intent);
            }

        });

        /*btToday.setOnClickListener(new View.OnClickListener(){
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
        });*/


        // Inflate the layout for this fragment
        return v;
    }

        // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (Activity) context;
        }

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void doTheUpAnimation(int fromLevel, int toLevel) {
        mLevel += LEVEL_DIFF;
        mImageDrawable.setLevel(mLevel);
        if (mLevel <= toLevel) {
            mUpHandler.postDelayed(animateUpImage, DELAY);
        } else {
            mUpHandler.removeCallbacks(animateUpImage);
            Tab1Fragment.this.fromLevel = toLevel;
        }
    }

    private void doTheDownAnimation(int fromLevel, int toLevel) {
        mLevel -= LEVEL_DIFF;
        mImageDrawable.setLevel(mLevel);
        if (mLevel >= toLevel) {
            mDownHandler.postDelayed(animateDownImage, DELAY);
        } else {
            mDownHandler.removeCallbacks(animateDownImage);
            Tab1Fragment.this.fromLevel = toLevel;
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
            Tab1Fragment.this.fromLevel = toLevel;

            mUpHandler.post(animateUpImage);
        } else {
            // cancel previous process first
            mUpHandler.removeCallbacks(animateUpImage);
            Tab1Fragment.this.fromLevel = toLevel;

            mDownHandler.post(animateDownImage);
        }

        new Thread() {
            public void run() {
                final int i = 30;
                try {
                    activity.runOnUiThread(new Runnable() {
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
            Tab1Fragment.this.fromLevel = toLevel;

            mUpHandler.post(animateUpImage);
        } else {
            // cancel previous process first
            mUpHandler.removeCallbacks(animateUpImage);
            Tab1Fragment.this.fromLevel = toLevel;

            mDownHandler.post(animateDownImage);
        }

        new Thread() {
            public void run() {
                final int i = iLevel - Integer.parseInt(String.valueOf(Math.round(Math.random() * 3)));
                try {
                    activity.runOnUiThread(new Runnable() {
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

        if (mDate.getDate() == 1) {
            btPrev.setEnabled(false);
            btPrev.setVisibility(View.INVISIBLE);
        }

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
            Tab1Fragment.this.fromLevel = toLevel;

            mUpHandler.post(animateUpImage);
        } else {
            // cancel previous process first
            mUpHandler.removeCallbacks(animateUpImage);
            Tab1Fragment.this.fromLevel = toLevel;

            mDownHandler.post(animateDownImage);
        }

        new Thread() {
            public void run() {
                final int i = iLevel + Integer.parseInt(String.valueOf(Math.round(Math.random() * 3)));
                try {
                    activity.runOnUiThread(new Runnable() {
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

        if (mDate.compareTo(new Date()) == 0) {
            btNext.setEnabled(false);
            btNext.setVisibility(View.INVISIBLE);
        }

        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        dTime = formatter.format ( mDate );

        Spannable spanText = Spannable.Factory.getInstance().newSpannable(dTime);
        spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 0,9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDate.setText(dTime);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
