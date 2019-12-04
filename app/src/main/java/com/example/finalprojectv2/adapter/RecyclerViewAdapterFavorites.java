package com.example.finalprojectv2.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojectv2.FavoriteMoviesActivity;
import com.example.finalprojectv2.MovieDetailActivity;
import com.example.finalprojectv2.MovieListActivity;
import com.example.finalprojectv2.R;
import com.example.finalprojectv2.data.DatabaseHandler;
import com.example.finalprojectv2.model.Movie;

import java.util.ArrayList;

public class RecyclerViewAdapterFavorites extends RecyclerView.Adapter<RecyclerViewAdapterFavorites.ViewHolder>{
    private Context context;
    private ArrayList<Movie> favoriteMoviesList;


    public RecyclerViewAdapterFavorites(Context context, ArrayList<Movie> favoriteMoviesList) {
        this.context = context;
        this.favoriteMoviesList = favoriteMoviesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Movie movie = favoriteMoviesList.get(position); //final???
        holder.TVName.setText(movie.getName());

        //create method for on click
        holder.BTRemove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // database
                DatabaseHandler db = new DatabaseHandler(context);
                movie.setFavorite(0);
                db.updateMovie(movie);
                Toast.makeText(context, "Removed from favorites, please reload", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteMoviesList.size();
    }

    //CLASS VIEWHOLDER
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView TVName;
        private Button BTRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TVName = itemView.findViewById(R.id.textViewName);
            BTRemove = itemView.findViewById(R.id.buttonRemove);
        }

        /*@Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            String type = favoriteMoviesList.get(position);
            Log.d("clicked", "onClick");

            Intent intent = new Intent(context, MovieDetailActivity.class); //.class because it is a class
            String movieTypeIntent = type;
            intent.putExtra("movieType",movieTypeIntent);
            context.startActivity(intent);
        }*/
    }



}
