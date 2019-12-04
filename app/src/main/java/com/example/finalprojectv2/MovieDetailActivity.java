package com.example.finalprojectv2;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalprojectv2.data.DatabaseHandler;
import com.example.finalprojectv2.model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.LinearLayout;

/**
 * An activity representing a single Movie detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MovieListActivity}.
 */
public class MovieDetailActivity extends AppCompatActivity {
    private LinearLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        //get intent with ID
        int id = getIntent().getIntExtra("movieId",0);

        fragmentContainer = findViewById(R.id.LinearLayoutContainer);



        Bundle bundle = new Bundle();
        bundle.putInt("movieId", id);


        //put fragment in fragment transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MovieDetailFragment detailFrag = new MovieDetailFragment();
        detailFrag.setArguments(bundle);
        ft.replace(R.id.LinearLayoutContainer, detailFrag);
        ft.commit();

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
