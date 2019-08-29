package com.mrigankprojects.recycler_test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Movie> mList;

    private String poster;
    View myView;
    private ImageView poster_path;

    public MyAdapter(Context context, ArrayList<Movie> mList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.mList=mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.v("check inside adapter", mList.get(position).getTitle());
        holder.serial_number.setText(mList.get(position).getTitle());
        String name = mList.get(position).getTitle();
        Log.v("Adapter Result: Title  ", name);
        poster = "http://image.tmdb.org/t/p/w342/" + mList.get(position).getPosterPath();
        //Log.v("Adapter Result: url  ", poster);
        Glide.with(myView.getContext())
                .load(poster)
                .into(poster_path);
    }

    @Override
    public int getItemCount() {
        //int size = mList.size();
        /*String str_size = String.valueOf(size);
        Log.v("check size", str_size);*/
        if (mList!= null) {
            return mList.size();
        } else {
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView serial_number;

        public MyViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
            serial_number = (TextView)itemView.findViewById(R.id.myRowText);
            poster_path = (ImageView)itemView.findViewById(R.id.myImage);
        }
    }
}
