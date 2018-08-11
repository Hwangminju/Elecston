package com.example.mjhwa.elecston.Fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ClipDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
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

import com.example.mjhwa.elecston.R;
import com.example.mjhwa.elecston.models.CustomGauge;
import com.example.mjhwa.elecston.views.TranActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TranFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TranFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TranFragment extends Fragment {

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

    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        imageView = (ImageView) getView().findViewById(R.id.elec_image);
        imageView.setImageResource(R.drawable.powerpoles);

        btPrev = (ImageButton) getView().findViewById(R.id.btPrev);
        btToday = (Button) getView().findViewById(R.id.btToday);
        btNext = (ImageButton) getView().findViewById(R.id.btNext);

        btPrev.setEnabled(false);
        btNext.setEnabled(false);

        tvDate = (TextView) getView().findViewById(R.id.tvDate);

        gauge = getActivity().findViewById(R.id.gauge);

        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        Date currentTime = new Date( );
        dTime = formatter.format ( currentTime );

        ImageView img = (ImageView) getView().findViewById(R.id.imageView1);
        mImageDrawable = (ClipDrawable) img.getDrawable();
        mImageDrawable.setLevel(0);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tran, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
            TranFragment.this.fromLevel = toLevel;
        }
    }

    private void doTheDownAnimation(int fromLevel, int toLevel) {
        mLevel -= LEVEL_DIFF;
        mImageDrawable.setLevel(mLevel);
        if (mLevel >= toLevel) {
            mDownHandler.postDelayed(animateDownImage, DELAY);
        } else {
            mDownHandler.removeCallbacks(animateDownImage);
            TranFragment.this.fromLevel = toLevel;
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
            TranFragment.this.fromLevel = toLevel;

            mUpHandler.post(animateUpImage);
        } else {
            // cancel previous process first
            mUpHandler.removeCallbacks(animateUpImage);
            TranFragment.this.fromLevel = toLevel;

            mDownHandler.post(animateDownImage);
        }

        new Thread() {
            public void run() {
                final int i = 35;
                try {
                    getActivity().runOnUiThread(new Runnable() {
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
            TranFragment.this.fromLevel = toLevel;

            mUpHandler.post(animateUpImage);
        } else {
            // cancel previous process first
            mUpHandler.removeCallbacks(animateUpImage);
            TranFragment.this.fromLevel = toLevel;

            mDownHandler.post(animateDownImage);
        }

        new Thread() {
            public void run() {
                final int i = iLevel - Integer.parseInt(String.valueOf(Math.round(Math.random() * 3)));
                try {
                    getActivity().runOnUiThread(new Runnable() {
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
            TranFragment.this.fromLevel = toLevel;

            mUpHandler.post(animateUpImage);
        } else {
            // cancel previous process first
            mUpHandler.removeCallbacks(animateUpImage);
            TranFragment.this.fromLevel = toLevel;

            mDownHandler.post(animateDownImage);
        }

        new Thread() {
            public void run() {
                final int i = iLevel + Integer.parseInt(String.valueOf(Math.round(Math.random() * 3)));
                try {
                    getActivity().runOnUiThread(new Runnable() {
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
    public void onResume() {
        super.onResume();
    }
}
