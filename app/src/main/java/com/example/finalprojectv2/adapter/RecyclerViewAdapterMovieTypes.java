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


import com.example.finalprojectv2.MovieListActivity;
import com.example.finalprojectv2.R;

import java.util.ArrayList;

public class RecyclerViewAdapterMovieTypes extends RecyclerView.Adapter<RecyclerViewAdapterMovieTypes.ViewHolder>{
    private Context context;
    private ArrayList<String> movieTypesList ;


    public RecyclerViewAdapterMovieTypes(Context context, ArrayList<String> movieTypesList) {
        this.context = context;
        this.movieTypesList = movieTypesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.type_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String type = movieTypesList.get(position);
        holder.TVType.setText(type);
    }

    @Override
    public int getItemCount() {
        return movieTypesList.size();
    }

    //CLASS VIEWHOLDER
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView TVType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            TVType = itemView.findViewById(R.id.textViewType);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            String type = movieTypesList.get(position);
            Log.d("clicked", "onClick");

            Intent intent = new Intent(context, MovieListActivity.class); //.class because it is a class
            String movieTypeIntent = type;
            intent.putExtra("movieType", movieTypeIntent);
            context.startActivity(intent);
        }
    }
}
