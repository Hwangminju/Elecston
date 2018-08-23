package com.example.mjhwa.elecston.TranFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mjhwa.elecston.R;
import com.example.mjhwa.elecston.views.SellActivity;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab3Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btSell;

    ListView listView;

    ArrayList<String> items = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public Tab3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Tab3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab3Fragment newInstance() {
        Tab3Fragment fragment = new Tab3Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tab3, container, false);

        listView = (ListView)v.findViewById(R.id.listview);
        // ArrayList<String> items = new ArrayList<>();

        btSell = (Button)v.findViewById(R.id.btSell);
        btSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SellActivity.class);
                startActivityForResult(intent, 3000);
            }
        });

        // Inflate the layout for this fragment
        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // int request = requestCode & 0xfffff;

        // Fragment fragment = getFragmentManager().findFragmentByTag("Tag");
        // fragment.onActivityResult(request, resultCode, data);

        if (resultCode != RESULT_OK) {
            return ;
        }

        // SellActivity에서 판매가격, 판매량 가져오기
        if (requestCode == 3000) {
            Toast.makeText(getContext(), "등록 완료", Toast.LENGTH_SHORT).show();
            int price = data.getIntExtra("price", 0);
            int elec = data.getIntExtra("elec", 0);

            items.add(String.valueOf(price) + "원/KW, 총 " + String.valueOf(elec)+ "KW");

            CustomAdapter adapter = new CustomAdapter(getActivity(), 0, items);
        }
    }

    private class CustomAdapter extends ArrayAdapter<String> {
        private ArrayList<String> items;

        public CustomAdapter(Context context, int textViewResourceId, ArrayList<String> objects) {
            super(context, textViewResourceId, objects);
            this.items = objects;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.listview_item, null);
            }

            // ImageView 인스턴스
            ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.num_one);

            TextView textView = (TextView) v.findViewById(R.id.textView);
            textView.setText(items.get(position));

            return v;
        }

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
