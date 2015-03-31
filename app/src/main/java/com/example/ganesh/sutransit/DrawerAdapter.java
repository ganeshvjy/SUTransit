package com.example.ganesh.sutransit;


import android.support.v7.widget.RecyclerView;

import android.view.View.OnClickListener;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Ganesh on 3/17/2015.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
        private final Context mcontext;
        private final List<Map<String,?>> mDataSet;
        OnItemClickListener mItemClickListener;
        private int mCurrentPosition;

        public DrawerAdapter(Context myContext, List<Map<String, ?>> myDataSet) {
            mcontext = myContext;
            mDataSet = myDataSet;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            public ImageView Icon;
            public TextView Title;
            public ImageView Line;
            public View vView;
            public int vViewType;


            public ViewHolder(View v, int viewtype){
                super(v);
                vView = v;
                vViewType = viewtype;
                Icon = (ImageView)v.findViewById(R.id.DIcon);
                Title = (TextView)v.findViewById(R.id.DTitle);
                //Line = (ImageView)v.findViewById(R.id.DLine);

                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemClickListener != null) {
                            mItemClickListener.onItemClick(v, getPosition());
                        }
                        mCurrentPosition = getPosition();
                        notifyDataSetChanged();
                    }
                });

            }

            public void bindMovieData(Map<String, ?> item, int position){
                if (position == mCurrentPosition)
                    vView.setBackgroundColor(Color.LTGRAY);
                else
                    vView.setBackgroundColor(0x00000000);
                switch(vViewType){
                    case DrawerData.TYPE1:
                        if (Icon != null)
                            Icon.setImageResource( ( (Integer)item.get("icon")).intValue());
                        if (Title !=null)
                            Title.setText((String)item.get("title"));
                    case DrawerData.TYPE2:
                        if (Line !=null)
                            Line.setImageResource(( (Integer)item.get("icon")).intValue());
                }

            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
            View v;
            ViewHolder vh;
            switch(viewType){
                case DrawerData.TYPE1:
                    v = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.drawer_list_model1, parent, false);
                    break;
                case DrawerData.TYPE2:
                    v = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.drawer_list_model2, parent, false);
                    break;
                default:
                    v = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.drawer_list_model1, parent, false);
                    break;
            }

            vh = new ViewHolder(v, viewType);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Map<String, ?>item = mDataSet.get(position);
            holder.bindMovieData(item,position);

        }
        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataSet.size();
        }

        public interface OnItemClickListener{
            public void onItemClick(View view, int position);
        }

        public void SetOnItemClickListener(final OnItemClickListener mItemClickListener){
            this.mItemClickListener = mItemClickListener;
        }

        @Override
        public int getItemViewType(int position){
            Map<String, ?> item = mDataSet.get(position);
            return (Integer) item.get("type");
        }
}

