package com.example.finalprojectv2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.finalprojectv2.adapter.RecyclerViewAdapterMovieList;
import com.example.finalprojectv2.data.DatabaseHandler;
import com.example.finalprojectv2.model.Movie;


import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {
    private ArrayList<Movie> movieArrayList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterMovieList recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        //get intent with type of movies
        String type = getIntent().getStringExtra("movieType");

        // database
        DatabaseHandler db = new DatabaseHandler(MovieListActivity.this);

        /*db.addMovie(new Movie("Jaws",8.0,
                "Jaws is a 1975 American thriller film directed by Steven Spielberg and based on Peter Benchley's 1974 novel of the same name.",
                "suspense",0,"jaws",1));

        db.addMovie(new Movie("The Shining",8.4,
                "The Shining is a 1980 psychological horror film produced and directed by Stanley Kubrick and co-written with novelist Diane Johnson. The film is based on Stephen King's 1977 novel of the same name",
                "horror",0,"shining",1));

        db.addMovie(new Movie("Zootopia",8.5,
                "Zootopia is a 2016 American 3D computer-animated comedy film produced by Walt Disney Animation Studios and released by Walt Disney Pictures.",
                "family",1,"zootopia",0));



        db.addMovie(new Movie("Midnight Sun",6.6,
                " Midnight Sun is a 2018 American romantic drama film directed by Scott Speer and written by Eric Kirsten, based on the 2006 Japanese film of the same name.",
                "romance",0,"midnightsun",1));*/

/*db.addMovie(new Movie("Avengers: Endgame",8.0,
                "Avengers: Endgame is a 2019 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures.",
                "action",1,"endgame",1));*/



        //initialize array list
        movieArrayList = new ArrayList<>();

        List<Movie> movieList = new ArrayList<>();
        if(type != null) {
            //add movies to array list
            movieList = db.getMoviesSpecifiedType(type);
        }
        else{
            movieList = db.getAllMovies();
        }
        //List<Movie> movieList = db.getAllMovies();

        Log.d("SIZE", "movie list size: " + movieList.size());

        for (Movie movie : movieList) {
            Log.d("movie", "ADD TO ARRAY LIST: " + movie.getMovieId());
            movieArrayList.add(movie);
        }

        //setup recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MovieListActivity.this));

        //set up RecyclerViewAdapter
        recyclerViewAdapter = new RecyclerViewAdapterMovieList(this, movieArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
    @Override
    //options menu
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

