package com.example.ganesh.sutransit;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Ganesh on 3/24/2015.
 */
public class MovieDataJson {

    List<Map<String,?>> moviesList;
    HashMap movieDetail;

    public void downloadMovieDataJSON(String url){
        MyUtility testUtility = new MyUtility();
        //String description = null;
        //double rating = 0.0;
        String imgURL = null;
        String name = null;
        //String id = null;
        moviesList = new ArrayList<Map<String,?>>();
        String moviesArray = testUtility.downloadJSON(url);
        if(moviesArray== null){
            Log.d("MyMsg", "Trouble loading URL" + url);
            return;
        }

        try{

            JSONArray moviesJsonArray = new JSONArray(moviesArray);
            for(int i=0;i<moviesJsonArray.length();i++){
                JSONObject movieJSONObj = (JSONObject) moviesJsonArray.get(i);
                if(movieJSONObj!=null){
                    name = (String) movieJSONObj.get("name");
                    //rating = Double.parseDouble(movieJSONObj.get("rating").toString());
                    imgURL = (String) movieJSONObj.get("url");
                    //description = (String) movieJSONObj.get("description");
                    //id = (String) movieJSONObj.get("id");
                }
                moviesList.add(createMovie(name, imgURL));
            }

        }
        catch(JSONException ex){
            Log.d("MyMsg", "JSONException");
        }
    }

    public void downloadMovieDetailJSON(String url){
        MyUtility testUtility = new MyUtility();
        String description = null;
        double rating = 0.0;
        String imgURL = null;
        String name = null;
        String id = null;
        movieDetail = new HashMap();
        String moviesDetailJSON = testUtility.downloadJSON(url);
        if(moviesDetailJSON== null){
            Log.d("MyMsg", "Trouble loading URL" + url);
            return;
        }

        try{

            //HashMap<String, String> map = new HashMap<String, String>();
            JSONObject jObject = new JSONObject(moviesDetailJSON);
            Iterator<?> keys = jObject.keys();

            while( keys.hasNext() ){
                String key = (String)keys.next();
                String value = jObject.getString(key);
                movieDetail.put(key, value);

            }

        }
        catch(JSONException ex){
            Log.d("MyMsg", "JSONException");
        }
    }

    public MovieDataJson(){
        moviesList = new ArrayList<Map<String,?>>();

    }
    public List<Map<String, ?>> getMoviesList() {
        return moviesList;
    }

    public int getSize(){
        return moviesList.size();
    }

    private HashMap createMovie(String name,  String url) {
        HashMap movie = new HashMap();
        //movie.put("id",id);
        movie.put("name", name);
       // movie.put("description", description);
        //movie.put("rating",rating);
        movie.put("url",url);
        return movie;
    }

    public void addItem(int position, HashMap item) {
        moviesList.add(position, item);
    }

    public void removeItem(int position){
        moviesList.remove(position);
    }

}
