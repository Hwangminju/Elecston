package com.example.mjhwa.elecston.SunFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mjhwa.elecston.R;
import com.example.mjhwa.elecston.models.CustomDialog;
import com.example.mjhwa.elecston.models.MyDialogListener;
import com.example.mjhwa.elecston.views.SunActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link YourFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link YourFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YourFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;

    TextView ta1, ta2, ta3, ta4, ta5, ta6, ta7;
    Button s1, s2, s3, s4, s5, s6, s7;

    public YourFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment YourFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static YourFragment newInstance() {
        YourFragment fragment = new YourFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_your, container, false);

        ta1 = (TextView) v.findViewById(R.id.ta1);
        ta2 = (TextView) v.findViewById(R.id.ta2);
        ta3 = (TextView) v.findViewById(R.id.ta3);
        ta4 = (TextView) v.findViewById(R.id.ta4);
        ta5 = (TextView) v.findViewById(R.id.ta5);
        ta6 = (TextView) v.findViewById(R.id.ta6);
        ta7 = (TextView) v.findViewById(R.id.ta7);

        ta1.setText(R.string.ta1);
        ta2.setText(R.string.ta2);
        ta3.setText(R.string.ta3);
        ta4.setText(R.string.ta4);
        ta5.setText(R.string.ta5);
        ta6.setText(R.string.ta6);
        ta7.setText(R.string.ta7);

        s1 = (Button) v.findViewById(R.id.sell_1);
        s2 = (Button) v.findViewById(R.id.sell_2);
        s3 = (Button) v.findViewById(R.id.sell_3);
        s4 = (Button) v.findViewById(R.id.sell_4);
        s5 = (Button) v.findViewById(R.id.sell_5);
        s6 = (Button) v.findViewById(R.id.sell_6);
        s7 = (Button) v.findViewById(R.id.sell_7);

        s1.setOnClickListener(btnClickListener);
        s2.setOnClickListener(btnClickListener);
        s3.setOnClickListener(btnClickListener);
        s4.setOnClickListener(btnClickListener);
        s5.setOnClickListener(btnClickListener);
        s6.setOnClickListener(btnClickListener);
        s7.setOnClickListener(btnClickListener);

        return v;
    }

    private Button.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.sell_1: // or
                case R.id.sell_2:
                case R.id.sell_3:
                case R.id.sell_4:
                case R.id.sell_5:
                case R.id.sell_6:
                case R.id.sell_7:

                    CustomDialog dialog = new CustomDialog(getContext());
                    dialog.setDialogListener(new MyDialogListener() {
                        @Override
                        public void onPositiveClicked(int buy) {
                            setResult(buy);
                        }

                        @Override
                        public void onNegativeClicked() {

                        }
                    });
                    dialog.show();
                    break;

                default:
                    break;
            }
        }
    };

    private void setResult(int buy){
        ((SunActivity)getActivity()).addSun(buy);
        int val = ((SunActivity)getActivity()).getSun();
        Toast.makeText(getActivity(), String.valueOf(buy) + " KW 구입 완료.\n태양광 보유량은 " + val + " KW 입니다.", Toast.LENGTH_LONG).show();
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

}
