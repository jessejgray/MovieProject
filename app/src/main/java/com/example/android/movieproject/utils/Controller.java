package com.example.android.movieproject.utils;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {//implements Callback<MovieResponse> {
    static final String BASE_URL = "https://api.themoviedb.org/";
public List<MovieModel> mMovieList;
    public void start(String sortBy,String apiKey) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MovieDbAPI movieDbAPI = retrofit.create(MovieDbAPI.class);

        Call<MovieResponse> call;
        if (sortBy.contains("popular")) {
            call = movieDbAPI.getMostPopularMovies(sortBy,apiKey);
        } else if (sortBy.contains("vote_average")) {
            call = movieDbAPI.getTopRatedMovies(sortBy,apiKey);
        } else {
            call = movieDbAPI.getMovie(sortBy,apiKey);
        }
//        call.enqueue(this);
        try {
            Response<MovieResponse> response = call.execute();
            if (response.errorBody() == null) {
                mMovieList = response.body().getMovieList();
            } else {
                Log.v("MovieDBService",response.errorBody().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        return mMovieList;
    }

//    @Override
//    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
//        if(response.isSuccessful()) {
//            MovieResponse changesList = response.body();
//            System.out.println(changesList.toString());
//            Log.d("Controller",changesList.toString());
//            mMovieList=changesList.getMovieList();
////            for (MovieModel change : changesList) {
////                System.out.println(change.getTitle());
////
////            }
//        } else {
//            System.out.println(response.errorBody());
//            mMovieList = null;
//            try{ Log.d("Controller",response.errorBody().string());}
//                catch(IOException e){
//                    Log.d("Controller","Cannot access reponse.errorBody");
//                }
//
//        }
//    }
//
//    @Override
//    public void onFailure(Call<MovieResponse> call, Throwable t) {
//        t.printStackTrace();
//        mMovieList = null;
//    }
}

