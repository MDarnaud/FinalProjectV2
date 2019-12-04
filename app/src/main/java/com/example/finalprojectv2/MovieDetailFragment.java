package com.example.finalprojectv2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import com.example.finalprojectv2.data.DatabaseHandler;
import com.example.finalprojectv2.model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDetailFragment extends Fragment implements View.OnClickListener {
    //REQUEST CODE
    private final int REQUEST_CODE = 1;

    //UI elements
    private TextView TV_type;
    private TextView TV_name;
    private TextView TV_rating;
    private TextView TV_description;
    private TextView TV_viewed;
    private Button BT_viewed;
    private Button BT_update;
    private Button BT_delete;
    private ImageView IV_movie;

    //movie object
    private Movie movie;

    //database
    private DatabaseHandler db;


    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        // database
        db = new DatabaseHandler(getContext());

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Added to favorites", Toast.LENGTH_LONG).show();
                movie.setFavorite(1);
                db.updateMovie(movie);
            }
        });

        //initialize elements
        TV_type = view.findViewById(R.id.TV_type);
        TV_name = view.findViewById(R.id.TV_name);
        TV_rating = view.findViewById(R.id.TV_rating);
        TV_description = view.findViewById(R.id.TV_desc);
        TV_viewed = view.findViewById(R.id.TV_viewed);
        BT_viewed = view.findViewById(R.id.BT_viewed);
        BT_update = view.findViewById(R.id.BT_update);
        BT_delete = view.findViewById(R.id.BT_delete);
        IV_movie = view.findViewById(R.id.IV_movie);

        int id = getArguments().getInt("movieId",0);


        //get movie specified by ID
        movie = db.getMovie(id);

        //check if move has been viewed, display text accordingly
        if(movie.getViewed() == 1){
            TV_viewed.setText("You have viewed this movie!");
        }
        else{
            TV_viewed.setText("You have not viewed this movie");
        }

        //get image from movie object
        Resources res = getResources();
        String mDrawableName = movie.getImagePath();
        int resID = res.getIdentifier(mDrawableName , "drawable", getContext().getPackageName());
        IV_movie.setImageResource(resID);

        TV_type.setText(movie.getMovieType());
        TV_name.setText(movie.getName());
        Double movieRating = movie.getRating();
        TV_rating.setText(movieRating.toString() + "/10");
        TV_description.setText(movie.getDescription());


        //set listener on buttons
        BT_viewed.setOnClickListener(this);
        BT_update.setOnClickListener(this);
        BT_delete.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View view) {
        db = new DatabaseHandler(getContext());
        switch(view.getId()){
            case R.id.BT_viewed:
                Toast.makeText(getContext(), "Added to viewed", Toast.LENGTH_LONG).show();
                movie.setViewed(1);
                db.updateMovie(movie);
                break;

            case R.id.BT_update:
                Intent intent = new Intent(getActivity(), UpdateFormActivity.class);
                int movieId = movie.getMovieId();
                intent.putExtra("movieId", movieId);
                startActivityForResult(intent, REQUEST_CODE);
                break;

            case R.id.BT_delete:
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                db.deleteMovie(movie);
                                Toast.makeText(getContext(), "Movie '" + movie.getName() + "' deleted", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getActivity(), MovieListActivity.class);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;
        }
    }


}


