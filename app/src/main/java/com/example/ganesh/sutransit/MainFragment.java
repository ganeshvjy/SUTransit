package com.example.ganesh.sutransit;


import android.content.Intent;
import android.support.v4.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by Ganesh on 3/17/2015.
 */
public class MainFragment extends Fragment {

    private OnButtonSelectedListener mListener;

    public MainFragment(){

    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_main, container, false);
        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int id = view.getId();
                switch (id){
                    case R.id.SearchBus:
                        break;
                    case R.id.NearestStop:
                        break;
                    case R.id.NotifyMe:
                        break;
                    case R.id.SpecialSchedules:
                        break;
                    case R.id.BusTracker:
                        Intent intent = new Intent(view.getContext(),BusTracker.class);
                        startActivity(intent);
                        break;

                    default:
                        break;
                }

            }
        };

        (rootView.findViewById(R.id.SearchBus)).setOnClickListener(onClickListener);
        (rootView.findViewById(R.id.NearestStop)).setOnClickListener(onClickListener);
        (rootView.findViewById(R.id.NotifyMe)).setOnClickListener(onClickListener);
        (rootView.findViewById(R.id.SpecialSchedules)).setOnClickListener(onClickListener);
        (rootView.findViewById(R.id.BusTracker)).setOnClickListener(onClickListener);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnButtonSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnButtonSelectedListener {
        public void onButtonItemSelected(int position);
    }

}
