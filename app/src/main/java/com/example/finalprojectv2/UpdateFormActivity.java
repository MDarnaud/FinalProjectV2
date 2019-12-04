package com.example.finalprojectv2;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalprojectv2.data.DatabaseHandler;
import com.example.finalprojectv2.model.Movie;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateFormActivity extends AppCompatActivity {

    //get activity elements
    private EditText ET_enterName;
    private EditText ET_enterRating;
    private EditText ET_enterDesc;
    private EditText ET_enterType;
    private EditText ET_enterPath;
    private Button BT_update;

    //get database
    private DatabaseHandler db;

    //create movie
    private Movie movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_form);

        //get all input
        ET_enterName = findViewById(R.id.ET_name);
        ET_enterRating = findViewById(R.id.ET_rating);
        ET_enterDesc = findViewById(R.id.ET_desc);
        ET_enterType = findViewById(R.id.ET_type);
        ET_enterPath = findViewById(R.id.ET_path);
        BT_update = findViewById(R.id.BT_update);

        //get intent with ID
        int id = getIntent().getIntExtra("movieId",0);

        //initialize database
        db = new DatabaseHandler(UpdateFormActivity.this);

        //get movie object
        movie = db.getMovie(id);

        //fill text views with movie information
        ET_enterName.setText(movie.getName());
        String originalRating = movie.getRating().toString();
        ET_enterRating.setText(originalRating);
        ET_enterDesc.setText(movie.getDescription());
        ET_enterType.setText(movie.getMovieType());
        ET_enterPath.setText(movie.getImagePath());

        //create method for on click
        BT_update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //set movie attributes to input
                movie.setName(ET_enterName.getText().toString());
                String ratingInput = ET_enterRating.getText().toString().trim();
                Double rating = Double.parseDouble(ratingInput);
                movie.setRating(rating);
                movie.setDescription(ET_enterDesc.getText().toString());
                movie.setMovieType(ET_enterType.getText().toString());
                movie.setImagePath(ET_enterPath.getText().toString());

                db.updateMovie(movie);
                Toast.makeText(UpdateFormActivity.this, "Movie '" + movie.getName() + "' updated", Toast.LENGTH_LONG).show();

                Intent intent = getIntent();
                intent.putExtra("movieId", movie.getMovieId());
                setResult(RESULT_OK, intent);
                finish();
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
