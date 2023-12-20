package com.company.laeventa.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.company.laeventa.MyApplication;
import com.company.laeventa.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewDetailsFragment extends Fragment {


    Context context;
    protected FragmentManager fragmentManager;
    protected ImageView editButton;

    protected GridView gridView;

    protected Boolean isAdmin = true;


    public OverviewDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context  = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview_details, container, false);

        isAdmin = MyApplication.getIsAdmin();
        editButton = view.findViewById(R.id.venue_overview_edit);

        gridView = view.findViewById(R.id.grid_view);

        if (isAdmin){
            editButton.setVisibility(View.VISIBLE);
        }

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fragmentManager = getActivity().getSupportFragmentManager();
        setButtons();
        initializeGridView();



    }

    private void initializeGridView() {

        //initializing a String array
        String[] array = new String[]{"one","two","three","four","five","six","seven"};
        //initializing an ArrayList from array
        List<String> list = new ArrayList<String>(Arrays.asList(array));

        //data bind GridView with ArrayAdapter
        gridView.setAdapter(new ArrayAdapter<String>(context,R.layout.rectangular_textview,list));

    }

    private void setButtons() {

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditOverviewFragment editOverviewFragment = (EditOverviewFragment) fragmentManager.findFragmentByTag("editOverview");

                if (editOverviewFragment == null) {
                    editOverviewFragment = new EditOverviewFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, editOverviewFragment, "editOverview").addToBackStack("editOverview").commit();
                } else {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, editOverviewFragment, "editOverview").addToBackStack("editOverview").commit();
                }
            }
        });
    }
}
