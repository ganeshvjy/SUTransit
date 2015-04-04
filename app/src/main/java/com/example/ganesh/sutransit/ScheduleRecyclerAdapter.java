package com.example.ganesh.sutransit;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

/**
 * Created by gthiagar on 2/8/15.
 */
public class ScheduleRecyclerAdapter extends RecyclerView.Adapter<ScheduleRecyclerAdapter.ViewHolder> {


    private List<Map<String,?>> mDataset;
    private Context context;
    OnItemClickListener mItemClickListener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ScheduleRecyclerAdapter(Context myContext, List<Map<String, ?>> myDataset){
        context = myContext;
        mDataset = myDataset;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
        //public void onItemLongClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mItemClickListener = mItemClickListener;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // each data item is just a string in this case
        // Items needed to populate the View related to the movie data

        public TextView mTitle;
        public ImageView mIcon;
        public TextView mDirector;
        public TextView mCast;
        public TextView mDesc;
        public RatingBar mRating;

        public ViewHolder(View v) {
            super(v);
            mTitle      = (TextView)    v.findViewById(R.id.busName);
            //mIcon       = (ImageView)   v.findViewById(R.id.busImage);
            //mDirector   = (TextView)    v.findViewById(R.id.movieDirector);
            //mCast       = (TextView)    v.findViewById(R.id.movieCast);
            mDesc       = (TextView)    v.findViewById(R.id.busDesc);
            //mRating     = (RatingBar)   v.findViewById(R.id.movieRating);

            v.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(mItemClickListener !=null){
                        mItemClickListener.onItemClick(v,getPosition());
                    }
                }
            });

        }

        private class AsyncImageTask extends AsyncTask<String, Void , Bitmap> {
            private final WeakReference<ImageView> imageViewReference;

            public AsyncImageTask(ImageView imgView){
                imageViewReference = new WeakReference<ImageView>(imgView);
            }



            @Override
            protected Bitmap doInBackground(String... urls){
                Bitmap bitmap = null;
                MyUtility testUtility = new MyUtility();
                for(String url:urls){
                    bitmap = testUtility.downloadImage(url);
                    //if(bitmap!= null){
                    //    mImageMemoryCache.put(url,bitmap);
                    //}
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {

                if(imageViewReference!=null && bitmap !=null){
                    final ImageView imageView = imageViewReference.get();
                    if(imageView!=null){
                        imageView.setImageBitmap(bitmap);
                    }
                }

            }
        }

        public void bindMovieData(Map<String,?> movie){
            //mCheck.setChecked((Boolean)movie.get("selection"));
            mTitle.setText((String)movie.get("name"));
            //mIcon.setImageResource((Integer)movie.get("image"));
            String url = (String) movie.get("url");
            // Code to Load image in the background thread

            AsyncImageTask imageTask = new AsyncImageTask(mIcon);
            imageTask.execute(new String[] {url});

            //mDesc.setText((String)movie.get("description"));
            //double rate = (Double) movie.get("rating");
            //int intRate = (int) rate;
            //mRating.setProgress(intRate/2);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }



    // Create new views (invoked by the layout manager)
    @Override
    public ScheduleRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_schedule_card,parent,false);
        return new ViewHolder(v);

}



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();

    }

    /*public int getItemViewType(int position){
        Map<String,?> movie = mDataset.get(position);
        if(Float.parseFloat((String.valueOf(movie.get("rating"))))> 8.0){
            return 2;
        }else{
            return 1;
        }

    }*/
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Map<String,?> movie = mDataset.get(position);
        holder.bindMovieData(movie);

    }
    public void removeItem(int position){
        mDataset.remove(position);
        notifyItemRemoved(position);}
}
