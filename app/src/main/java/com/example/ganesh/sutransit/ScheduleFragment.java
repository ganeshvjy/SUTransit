package com.example.ganesh.sutransit;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by Ganesh on 4/4/2015.
 */
public class ScheduleFragment extends Fragment {


    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final MovieDataJson movieData = new MovieDataJson();
    private RecyclerView mRecyclerView;
    private ScheduleRecyclerAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnRecyclerViewItemSelectedListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        /*try {
            movieData = new MovieDataJson(getActivity());
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        mRecyclerAdapter = new ScheduleRecyclerAdapter(getActivity(), movieData.moviesList);
        AsyncRecyclerTask downloadJSON = new AsyncRecyclerTask(mRecyclerAdapter);
        String url = "http://www.example.com/pdfs/";
        downloadJSON.execute(new String[]{url});

        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_special_schedules, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.ScheduleList);

        int option = getArguments().getInt(ARG_SECTION_NUMBER);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerAdapter.SetOnItemClickListener(new ScheduleRecyclerAdapter.OnItemClickListener(){
            @Override
            public void onItemClick( View v, int position){
                HashMap<String,?> movie= (HashMap<String,?>)movieData.moviesList.get(position);

                String url = (String) movie.get("url");

                if(url!=null){
                    AsyncDetailTask downloadDetail = new AsyncDetailTask(mListener);
                    downloadDetail.execute(new String[]{url});
                }
            }

        });

        return rootView;
    }


    public class AsyncRecyclerTask extends AsyncTask<String, Void, MovieDataJson> {

        //@Override
        // protected void onPreExecute() {
        //setProgressBarIndeterminateVisibility(true);
        //}
        private final WeakReference<ScheduleRecyclerAdapter> adapterReference;

        public AsyncRecyclerTask(ScheduleRecyclerAdapter adapter){
            adapterReference = new WeakReference<ScheduleRecyclerAdapter>(adapter);
        }

        @Override
        protected MovieDataJson doInBackground(String... params) {
            MovieDataJson threadMovieData = new MovieDataJson();
            for(String url:params){
                threadMovieData.downloadMovieDataJSON(url);
            }
            return threadMovieData;
        }

        @Override
        protected void onPostExecute(MovieDataJson threadMovieData) {
            movieData.moviesList.clear();

            for(int i =0;i<threadMovieData.moviesList.size();i++){
                movieData.moviesList.add(threadMovieData.moviesList.get(i));
            }

            if(adapterReference!=null){
                final ScheduleRecyclerAdapter adapter = adapterReference.get();
                if(adapter!=null){
                    adapter.notifyDataSetChanged();
                }
            }

        }
    }
    public static ScheduleFragment newInstance(int sectionNumber) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScheduleFragment() {
        // Required empty public constructor
    }

    private class AsyncDetailTask extends AsyncTask<String,Void,HashMap>{

        private final WeakReference<OnRecyclerViewItemSelectedListener> weakListener;

        public AsyncDetailTask(OnRecyclerViewItemSelectedListener listener){
            weakListener = new WeakReference<OnRecyclerViewItemSelectedListener>(listener);
        }

        @Override
        protected HashMap doInBackground(String... urls){

            HashMap movie = new HashMap();

            MovieDataJson threadMovieData = new MovieDataJson();
            for(String url:urls){
                threadMovieData.downloadMovieDetailJSON(url);
            }

            movie = threadMovieData.movieDetail;
            return movie;

            //return threadMovieData;

        }

        @Override
        protected void onPostExecute(HashMap movie){
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ScheduleDetails.newInstance(movie))
                    .addToBackStack(null)
                    .commit();
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnRecyclerViewItemSelectedListener) activity;
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


    public interface OnRecyclerViewItemSelectedListener {
        // TODO: Update argument type and name
        public void onItemSelected(int position, HashMap<String, ?> movie);

    }

}
