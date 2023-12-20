package com.company.laeventa.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.company.laeventa.MyApplication;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.company.laeventa.R;
import com.company.laeventa.adapters.DetailsPagerAdapter;
import com.company.laeventa.adapters.ImagesPagerAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VenueDetailFragment extends Fragment {

    Context context;
    protected LinearLayout callButton;

    protected TextView imageNumberTextView;
    protected ImageView favourite;
    protected ProgressBar favouriteProgress;

    protected RelativeLayout venueContainer;
    protected AVLoadingIndicatorView aviProgressBar;
    protected FrameLayout aviProgressBarLayout;

    protected TextView title;

    protected DetailsPagerAdapter pagerAdapter;

    protected boolean isSports;
    protected ArrayList<String> profileImagesList;



    public VenueDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        profileImagesList = new ArrayList<String>(5);
        feedProfileImagesData();
        setSportsBoolean();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_venue_detail, container, false);
        callButton = view.findViewById(R.id.call_button);
        imageNumberTextView = view.findViewById(R.id.imageNumberTextView);
        ViewPager viewPager = view.findViewById(R.id.profile_pager);
        intializeProfilePager(viewPager);

        venueContainer = view.findViewById(R.id.container);
        aviProgressBar = view.findViewById(R.id.avi_progress_bar);
        startProgressBar();

        if (!(MyApplication.getIsAdmin())){

            favourite = getActivity().findViewById(R.id.favourite);
            favouriteProgress = getActivity().findViewById(R.id.favourite_progress);
            favourite.setVisibility(View.VISIBLE);
            favouriteProgress.setVisibility(View.GONE);
            title = getActivity().findViewById(R.id.page_title);
        }

        return view;
    }

    private void startProgressBar() {

        venueContainer.setVisibility(View.GONE);
        aviProgressBar.setVisibility(View.VISIBLE);
       // aviProgressBar.show();
        delayAvi();

    }

    private void stopProgressBar(){

        venueContainer.setVisibility(View.VISIBLE);
        aviProgressBar.setVisibility(View.GONE);
       // aviProgressBar.hide();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        intializeTabLayout();

        setButtons();

        if (!(MyApplication.getIsAdmin())){
            title.setText("Hotel Virndavan xxx");
        }
    }

    private void feedProfileImagesData() {

        profileImagesList.add("https://images.unsplash.com/photo-1535827841776-24afc1e255ac?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=675&q=80");
        profileImagesList.add("https://images.unsplash.com/photo-1455587734955-081b22074882?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80");
        profileImagesList.add("https://images.unsplash.com/photo-1529290130-4ca3753253ae?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1055&q=80");
        profileImagesList.add("https://images.unsplash.com/photo-1538683270504-3d09ad7ae739?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");
        profileImagesList.add("https://images.unsplash.com/photo-1490122417551-6ee9691429d0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80");


    }

    private void intializeProfilePager(ViewPager viewPager) {

        ImagesPagerAdapter imagesPagerAdapter = new ImagesPagerAdapter(getContext(), profileImagesList, imageNumberTextView);
        viewPager.setAdapter(imagesPagerAdapter);

    }

    private void setButtons() {

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + "918483870189"));
                getActivity().startActivity(intent);
            }
        });

        if (!(MyApplication.getIsAdmin())){

            favourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favouriteProgress.setVisibility(View.VISIBLE);
                    favourite.setVisibility(View.GONE);
                    delay();
                }
            });
        }

    }

    private void intializeTabLayout() {

        final TabLayout tabLayout = getView().findViewById(R.id.tabLayout);
        final ViewPager viewPager = getView().findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        //   tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        pagerAdapter = new DetailsPagerAdapter(context, getChildFragmentManager(), tabLayout.getTabCount(), isSports);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
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
                favourite.setVisibility(View.VISIBLE);
                favouriteProgress.setVisibility(View.GONE);
                favourite.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_on));
            }
        }, 2000);
    }

    private void delayAvi() {
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                stopProgressBar();
            }
        }, 2000);
    }

    private void setSportsBoolean() {
        isSports = true;
    }

}
