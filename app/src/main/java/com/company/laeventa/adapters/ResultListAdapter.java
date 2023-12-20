package com.company.laeventa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.laeventa.R;
import com.company.laeventa.models.Venue;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ResultListAdapter extends BaseAdapter {

    protected Context context;
    protected List<Venue> venueList;



    public ResultListAdapter(Context context, List<Venue> resultList) {
        this.context = context;
        this.venueList = resultList;
    }


    @Override
    public int getCount() {
        return venueList.size();
    }

    @Override
    public Object getItem(int position) {
        return venueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.search_result_view, parent, false);
        }

        TextView x = convertView.findViewById(R.id.venue_name);
        ImageView y = convertView.findViewById(R.id.venue_image);
        x.setText("Hotel xxxxx");
        Picasso.get().load(venueList.get(position).getImage()).error( R.drawable.ic_error_loading )
                .placeholder( R.drawable.progress_animation_picasso ).into(y);

        return convertView;
    }
}
