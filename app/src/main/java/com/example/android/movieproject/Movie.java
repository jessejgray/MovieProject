package com.example.android.movieproject;

import com.example.android.movieproject.utils.MovieUtils;

public class Movie {
    private long mMovieId;
    private String mMovieTitle;
    private String mPosterUrl;
    private String mReleaseDate;
    private float mVoteAvg;
    private String mPlot;

    public Movie(long movieId, String movieTitle, String posterImage, String releaseDate, float voteAverage, String plot){
        mMovieId = movieId;
        mMovieTitle = movieTitle;
        mPosterUrl = posterImage;
        mReleaseDate = releaseDate;
        mVoteAvg = voteAverage;
        mPlot = plot;
    }

    public long getMovieId() { return mMovieId;}
    public String getMovieTitle() { return mMovieTitle;}
    public String getPosterUrl() { return mPosterUrl;}
    public String getReleaseDate() { return MovieUtils.convertYYYY_MM_DD_MiddleEndian(mReleaseDate);}
    public float getVoteAverage() { return mVoteAvg;}
    public String getPlot() { return mPlot;}

}
