package com.company.laeventa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.company.laeventa.R;
import com.company.laeventa.models.Area;

import java.util.List;

public class AreasAdapter extends BaseAdapter {

    protected Context context;
    protected List<Area> areaList;

    public AreasAdapter(Context context, List<Area> areaList) {
        this.context = context;
        this.areaList = areaList;
    }

    @Override
    public int getCount() {
        return areaList.size();
    }

    @Override
    public Object getItem(int position) {
        return areaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.area_list_item, parent, false);
        }

        TextView x = convertView.findViewById(R.id.venue_name);
        x.setText("Party Park");

        return convertView;
    }
}
