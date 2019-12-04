package com.example.finalprojectv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.finalprojectv2.adapter.RecyclerViewAdapterMovieTypes;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieTypesActivity extends AppCompatActivity {
    private ArrayList<String> movieTypesList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterMovieTypes recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_types);


        movieTypesList = new ArrayList<>();
        //initialize array list
        movieTypesList.add("action");
        movieTypesList.add("drama");
        movieTypesList.add("comedy");
        movieTypesList.add("romance");
        movieTypesList.add("suspense");
        movieTypesList.add("family");
        movieTypesList.add("horror");
        movieTypesList.add("sci-fi");
        movieTypesList.add("international");

        //setup recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MovieTypesActivity.this));

        //set up RecyclerViewAdapter
        recyclerViewAdapter = new RecyclerViewAdapterMovieTypes(this, movieTypesList);
        recyclerView.setAdapter(recyclerViewAdapter);


    }
    //options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }
    @Override
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
