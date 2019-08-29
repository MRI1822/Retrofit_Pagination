package com.mrigankprojects.recycler_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    MyAdapter myAdapter;
    List<String> myList;
    RecyclerView list;
    Retrofit retrofit;
    APIInterface apiInterface;
    ArrayList<Movie> movieList;
    private static final String API_KEY = "78adf61cd991fec888c055105c148a44";


    List<String> myMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* String arr[] = {"Movie 1", "Movie 2", "Movie 3", "Movie 4", "Movie 5",
                "Movie 6", "Movie 7", "Movie 8", "Movie 9", "Movie 10", "Movie 11",
                "Movie 12", "Movie 13", "Movie 14", "Movie 15", "Movie 16"};

        myMovieList = new ArrayList<>();
        for (int i=0; i<arr.length; i++){
            myMovieList.add(arr[i]);
            //Log.v("check", myMovieList.get(i));
        }*/

        retrofitMethod();
    }

    public void retrofitMethod(){
        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(APIInterface.class);
        Call<MovieResponse> call = apiInterface.getMovieList(API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.v("MainActivity", "Inside");
               // Log.v("MainActivity Inside", response.body().getMovieResult());
                movieList= response.body().getMovieResult();
                makeView();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"MainActivity Failed", Toast.LENGTH_SHORT).show();
                Log.v("MainActivity", "Failure");
            }
        });
    }

    public void makeView(){
        list = (RecyclerView)findViewById(R.id.myRecyclerList);
        list.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this, movieList);
        list.setAdapter(myAdapter);
    }

}
