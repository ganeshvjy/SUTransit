package com.example.ganesh.sutransit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleDetails extends Fragment {

    private static final String ARG_MOVIE= "mData";

    public ScheduleDetails() {
        // Required empty public constructor
    }

    public static ScheduleDetails newInstance(HashMap<String,?> movie) {
        ScheduleDetails fragment = new ScheduleDetails();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_schedule_details, container, false);

        WebView webview = (WebView) rootView.findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        String pdf = "https://ganeshvjy.files.wordpress.com/2014/12/project5-cbis.pdf";
        webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + pdf);

        return rootView;
    }


}
