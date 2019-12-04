package com.example.finalprojectv2.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.finalprojectv2.R;
import com.example.finalprojectv2.model.Movie;
import com.example.finalprojectv2.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.KEY_NAME + " TEXT, "
                + Util.KEY_RATING + " DOUBLE, "
                + Util.KEY_DESCRIPTION + " TEXT, "
                + Util.KEY_TYPE + " TEXT, "
                + Util.KEY_VIEWED + " INTEGER, "
                + Util.KEY_IMAGE + " TEXT, "
                + Util.KEY_FAVORITE + " INTEGER"
                + ")";
        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String DROP_TABLE = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        onCreate(db);
    }

    //add a contact
    public void addMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, movie.getName());
        values.put(Util.KEY_RATING, movie.getRating());
        values.put(Util.KEY_DESCRIPTION, movie.getDescription());
        values.put(Util.KEY_TYPE, movie.getMovieType());
        values.put(Util.KEY_VIEWED, movie.getViewed());
        values.put(Util.KEY_IMAGE, movie.getImagePath());
        values.put(Util.KEY_FAVORITE, movie.getFavorite());


        db.insert(Util.TABLE_NAME, null, values);


        Log.d("handler", "addMovie: " + " item added");

        db.close();
    }

    public int updateMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, movie.getName());
        values.put(Util.KEY_RATING, movie.getRating());
        values.put(Util.KEY_DESCRIPTION, movie.getDescription());
        values.put(Util.KEY_TYPE, movie.getMovieType());
        values.put(Util.KEY_VIEWED, movie.getViewed());
        values.put(Util.KEY_IMAGE, movie.getImagePath());
        values.put(Util.KEY_FAVORITE, movie.getFavorite());


        return db.update(Util.TABLE_NAME, values, Util.KEY_ID + "=?", new String[]{String.valueOf(movie.getMovieId())});
    }

    public void deleteMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?", new String[]{String.valueOf(movie.getMovieId())});
    }

    //get a movie
    public Movie getMovie(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_RATING, Util.KEY_DESCRIPTION, Util.KEY_TYPE, Util.KEY_VIEWED, Util.KEY_IMAGE, Util.KEY_FAVORITE}, Util.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Movie movie = new Movie();
            movie.setMovieId(cursor.getInt(0));
            movie.setName(cursor.getString(1));
            movie.setRating(cursor.getDouble(2));
            movie.setDescription(cursor.getString(3));
            movie.setMovieType(cursor.getString(4));
            movie.setViewed(cursor.getInt(5)); //SQLite does not have a separate Boolean storage class. Instead, Boolean values are stored as integers 0 (false) and 1 (true).
            movie.setImagePath(cursor.getString(6));
            movie.setFavorite(cursor.getInt(7));

            db.close();
            return movie;
        } else {
            db.close();
            return null;
        }
    }

    public int getMovieCount() {
        SQLiteDatabase db = this.getReadableDatabase();

        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }

    public ArrayList<Movie> getAllMovies() {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Movie> movieList = new ArrayList<>();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setMovieId(Integer.parseInt(cursor.getString(0)));
                movie.setName(cursor.getString(1));
                movie.setRating(cursor.getDouble(2));
                movie.setDescription(cursor.getString(3));
                movie.setMovieType(cursor.getString(4));
                movie.setViewed(cursor.getInt(5));
                movie.setImagePath(cursor.getString(6));
                movie.setFavorite(cursor.getInt(7));

                movieList.add(movie);
            } while (cursor.moveToNext());
        }
        return movieList;
    }


    public List<Movie> getMoviesSpecifiedType(String type) {

        SQLiteDatabase db = this.getReadableDatabase();

        List<Movie> movieList = new ArrayList<>();


        Cursor cursor = db.rawQuery("SELECT * FROM "+ Util.TABLE_NAME+" WHERE "+ Util.KEY_TYPE +" = ?; ", new String[] {String.valueOf(type)});

        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setMovieId(Integer.parseInt(cursor.getString(0)));
                movie.setName(cursor.getString(1));
                movie.setRating(cursor.getDouble(2));
                movie.setDescription(cursor.getString(3));
                movie.setMovieType(cursor.getString(4));
                movie.setViewed(cursor.getInt(5));
                movie.setImagePath(cursor.getString(6));
                movie.setFavorite(cursor.getInt(7));

                movieList.add(movie);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return movieList;
    }

    public List<Movie> getFavoriteMovies() {

        SQLiteDatabase db = this.getReadableDatabase();

        List<Movie> movieList = new ArrayList<>();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME + " WHERE " + Util.KEY_FAVORITE + " = 1";

        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setMovieId(Integer.parseInt(cursor.getString(0)));
                movie.setName(cursor.getString(1));
                movie.setRating(cursor.getDouble(2));
                movie.setDescription(cursor.getString(3));
                movie.setMovieType(cursor.getString(4));
                movie.setViewed(cursor.getInt(5));
                movie.setImagePath(cursor.getString(6));
                movie.setFavorite(cursor.getInt(7));

                movieList.add(movie);
            } while (cursor.moveToNext());
        }
        return movieList;
    }
}

