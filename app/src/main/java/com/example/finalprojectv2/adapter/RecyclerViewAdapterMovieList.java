package com.example.finalprojectv2.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojectv2.MovieDetailActivity;
import com.example.finalprojectv2.R;
import com.example.finalprojectv2.model.Movie;

import java.util.ArrayList;

public class RecyclerViewAdapterMovieList extends RecyclerView.Adapter<RecyclerViewAdapterMovieList.ViewHolder>{
    private Context context;
    private ArrayList<Movie> movieList ;


    public RecyclerViewAdapterMovieList(Context context, ArrayList<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.TVName.setText(movie.getName());
        Double rating = movie.getRating();
        holder.TVRating.setText((rating.toString()) + "/10");
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    //CLASS VIEWHOLDER
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView TVName;
        private TextView TVRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            TVName = itemView.findViewById(R.id.textViewType);
            TVRating = itemView.findViewById(R.id.textViewRating);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Movie movie = movieList.get(position);
            Log.d("clicked", "onClick");

            Intent intent = new Intent(context, MovieDetailActivity.class); //.class because it is a class
            int movieId = movie.getMovieId();
            intent.putExtra("movieId", movieId);
            context.startActivity(intent);
        }
    }
}
