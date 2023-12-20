package com.company.laeventa.fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.company.laeventa.MyApplication;
import com.company.laeventa.R;
import com.company.laeventa.adapters.AreasAdapter;
import com.company.laeventa.models.Area;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AreasFragment extends Fragment {

    protected Context context;
    protected FragmentManager fragmentManager;

    protected AreaDetailsFragment areaDetailsFragment;

    protected Button addAreaButton;

    protected ListView areaListView;
    protected List<Area> areaList;
    protected LinearLayout progressLayout;

    public AreasFragment() {
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
        View view = inflater.inflate(R.layout.fragment_areas, container, false);

        areaListView = view.findViewById(R.id.area_list_view);
        progressLayout = view.findViewById(R.id.customized_progressbar);
        addAreaButton = view.findViewById(R.id.add_area_button);

        if(MyApplication.getIsAdmin()){
            addAreaButton.setVisibility(View.VISIBLE);
        }

        areaList = new ArrayList<Area>(10);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        feedData();
        progressLayout.setVisibility(View.VISIBLE);
        areaListView.setVisibility(View.GONE);

        fragmentManager = getActivity().getSupportFragmentManager();

        AreasAdapter areasAdapter = new AreasAdapter(context,areaList);
        areaListView.setAdapter(areasAdapter);

        areaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                areaDetailsFragment = (AreaDetailsFragment) fragmentManager.findFragmentByTag("area_details");
                if (areaDetailsFragment == null) {
                    areaDetailsFragment = new AreaDetailsFragment();
                    fragmentManager.beginTransaction().setCustomAnimations( android.R.anim.fade_in,android.R.anim.fade_out).replace(R.id.fragment_container, areaDetailsFragment, "area_details").addToBackStack("area_details").commit();
                } else {
                    fragmentManager.beginTransaction().setCustomAnimations( android.R.anim.fade_in,android.R.anim.fade_out).replace(R.id.fragment_container, areaDetailsFragment, "area_details").addToBackStack("area_details").commit();
                }
            }
        });

        addAreaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditAreaFragment editAreaFragment = (EditAreaFragment) fragmentManager.findFragmentByTag("editArea");

                if (editAreaFragment == null) {
                    editAreaFragment = new EditAreaFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, editAreaFragment, "editArea").addToBackStack("editArea").commit();
                } else {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, editAreaFragment, "editAreae").addToBackStack("editArea").commit();
                }
            }
        });


        delay();
    }

    private void feedData() {

        Area area1 = new Area("Lawn Area","2300");
        Area area2 = new Area("Park","100");
        Area area3 = new Area("Conference Hall","4500");
        Area area4 = new Area("Party Area","120");
        areaList.add(area1);
        areaList.add(area2);
        areaList.add(area3);
        areaList.add(area4);
    }

    private void delay() {
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                progressLayout.setVisibility(View.GONE);
                areaListView.setVisibility(View.VISIBLE);
            }
        }, 500);
    }

}
