package com.company.laeventa.fragments;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.laeventa.R;
import com.company.laeventa.adapters.ResultAdapter;
import com.company.laeventa.models.Venue;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VenueSearchFragment extends Fragment {

    protected Context context;

    protected RecyclerView recyclerView;
    protected List<Venue> venueList;

    public VenueSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_venue_search, container, false);

        recyclerView = view.findViewById(R.id.recycler_list);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ResultAdapter resultAdapter = new ResultAdapter(context, venueList);
        recyclerView.setAdapter(resultAdapter);
    }
}
