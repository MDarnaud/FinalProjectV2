package com.example.finalprojectv2;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalprojectv2.data.DatabaseHandler;
import com.example.finalprojectv2.model.Movie;

public class AddFormActivity extends AppCompatActivity {
    //get activity elements
    private EditText ET_enterName;
    private EditText ET_enterRating;
    private EditText ET_enterDesc;
    private EditText ET_enterType;
    private CheckBox CB_enterViewed;
    private EditText ET_enterPath;
    private CheckBox CB_enterFavorite;
    private Button BT_add;

    //get database
    // database
    DatabaseHandler db = new DatabaseHandler(AddFormActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form);

        //get all input
        ET_enterName = findViewById(R.id.ET_name);
        ET_enterRating = findViewById(R.id.ET_rating);
        ET_enterDesc = findViewById(R.id.ET_desc);
        ET_enterType = findViewById(R.id.ET_type);
        CB_enterViewed = findViewById(R.id.CB_viewed);
        ET_enterPath = findViewById(R.id.ET_path);
        CB_enterFavorite = findViewById(R.id.CB_favorite);
        BT_add = findViewById(R.id.BT_add);


        //create method for on click
        BT_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //create movie
                final Movie movie = new Movie();
                movie.setName(ET_enterName.getText().toString());
                String ratingInput = ET_enterRating.getText().toString().trim();
                Double rating = Double.parseDouble(ratingInput);
                movie.setRating(rating);
                movie.setDescription(ET_enterDesc.getText().toString());
                movie.setMovieType(ET_enterType.getText().toString());
                //check box viewed
                int viewed = 0; //false
                if(CB_enterViewed.isChecked()){
                    viewed = 1; //true
                }
                movie.setViewed(viewed);
                //path
                movie.setImagePath(ET_enterPath.getText().toString());
                //check box
                int favorite = 0; //false
                if(CB_enterFavorite.isChecked()){
                    favorite = 1; //true
                }
                movie.setFavorite(favorite);

                db.addMovie(movie);
                Toast.makeText(AddFormActivity.this, "Movie '" + movie.getName() + "' added", Toast.LENGTH_LONG).show();
            }
        });
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
