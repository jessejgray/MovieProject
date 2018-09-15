package com.example.android.movieproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jcgray on 8/5/18.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>{

    private final List<Movie> movies;
    private final OnItemClickListener listener;

    public MovieListAdapter(List<Movie> movies, OnItemClickListener listener){
        this.movies=movies;
        this.listener=listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(movies.get(position),listener);
    }

    public void clear(){
       movies.clear();
    }

    public void addAll(List<Movie> movies){
        this.movies.addAll(movies);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MovieViewHolder(v);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder{
        private ImageView movieImg;

        public MovieViewHolder(View v){
            super(v);
            movieImg = v.findViewById(R.id.movie_list_item_image);
        }
        
        public void bind(final Movie movie, final OnItemClickListener listener){
            Picasso.with(itemView.getContext()).load(movie.getPosterUrl())
                    .placeholder(R.drawable.imageunavailabe)
                    .error(R.drawable.imageunavailabe)
                    .into(movieImg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie);
                }
            });
        }
    }
}
