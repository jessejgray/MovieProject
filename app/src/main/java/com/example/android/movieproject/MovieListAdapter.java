package com.example.android.movieproject;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.movieproject.provider.MovieContract;

/**
 * Created by jcgray on 8/5/18.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>{

    private Context mContext;
    private Cursor mCursor;

    public MovieListAdapter(Context context,Cursor cursor){
        this.mContext=context;
        this.mCursor=cursor;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        mCursor.moveToPosition(position);

        int movieListImageIndex = mCursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_POSTER);
        int movieTitleIndex = mCursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_TITLE);
        int releaseDateIndex = mCursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_RELEASE_DATE);
        int voteAverageIndex = mCursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE);
        int voteDescIndex = mCursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_VOTE_DESCRIPTION);
        int plotIndex = mCursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_PLOT);

        int movieImageRes = mCursor.getInt(movieListImageIndex);
        String movieTitle = mCursor.getString(movieTitleIndex);
        long releaseDate = mCursor.getLong(releaseDateIndex);
        double voteAverage = mCursor.getDouble(voteAverageIndex);
        String voteDesc = mCursor.getString(voteDescIndex);
        String plot = mCursor.getString(plotIndex);

        /*int imgRes = PlantUtils.getPlantImageRes(mContext, timeNow - createdAt, timeNow - wateredAt, plantType);

        holder.plantImageView.setImageResource(imgRes);
        holder.plantNameView.setText(String.valueOf(plantId));
        holder.plantImageView.setTag(plantId);
        */
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;
        if (mCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) return 0;
        return mCursor.getCount();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view=inflater.inflate(R.layout.list_item,parent,false);
        return new MovieViewHolder(view);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{
        private ImageView movieImg;
        public MovieViewHolder(View v){
            super(v);
            movieImg = (ImageView) v.findViewById(R.id.plot_tv);
        }
    }
}