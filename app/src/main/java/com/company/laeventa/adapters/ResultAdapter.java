package com.company.laeventa.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.company.laeventa.R;
import com.company.laeventa.models.Venue;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder>{


    private LayoutInflater inflater;
    private List<Venue> venueList;

    public ResultAdapter(Context context, List<Venue> venueList) {

        venueList = new ArrayList<Venue>(50);
        this.venueList = venueList;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.search_result_view,viewGroup,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Venue selectedVenue = venueList.get(i);
        holder.setData(selectedVenue,i);
    }

    @Override
    public int getItemCount() {
        return venueList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView venueName;
        private RatingBar ratingBar;
        private TextView capacity;
        private ImageView favourite;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            venueName = itemView.findViewById(R.id.venue_name);
        }

        public void setData(Venue selectedVenue, int i) {
            this.venueName.setText(selectedVenue.getName());
           // this.capacity.setText("130-242");
           // this.ratingBar.setRating(3.2f);

        }
    }


}