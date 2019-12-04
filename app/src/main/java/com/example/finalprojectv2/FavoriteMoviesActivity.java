package com.example.finalprojectv2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojectv2.adapter.RecyclerViewAdapterFavorites;
import com.example.finalprojectv2.adapter.RecyclerViewAdapterMovieTypes;
import com.example.finalprojectv2.data.DatabaseHandler;
import com.example.finalprojectv2.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMoviesActivity extends AppCompatActivity {
    private ArrayList<Movie> favoriteMoviesArrayList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterFavorites recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);


        //initialize array list
        favoriteMoviesArrayList = new ArrayList<>();

        // database
        DatabaseHandler db = new DatabaseHandler(FavoriteMoviesActivity.this);

        //setup recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(FavoriteMoviesActivity.this));

        //put favorite movies in list
        List<Movie> movieList = db.getFavoriteMovies();

        for (Movie movie : movieList) {
            Log.d("movie", "ADD TO ARRAY LIST: " + movie.getMovieId());
            favoriteMoviesArrayList.add(movie);
        }

        //set up RecyclerViewAdapter
        recyclerViewAdapter = new RecyclerViewAdapterFavorites(this, favoriteMoviesArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    //options menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.movieTypes:
                startActivity(new Intent(this, MovieTypesActivity.class));
                return true;
            case R.id.favoriteMovies:
                startActivity(new Intent(this, FavoriteMoviesActivity.class));
                return true;
            case R.id.addMovie:
                startActivity(new Intent(this, AddFormActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
