package com.mrigankprojects.recycler_test;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface APIInterface {

    @GET("movie/top_rated")
    Call<MovieResponse> getMovieList(@Query("api_key") String apiKey);
}