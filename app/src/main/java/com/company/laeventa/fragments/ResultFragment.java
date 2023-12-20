package com.company.laeventa.fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.company.laeventa.R;
import com.company.laeventa.adapters.ResultListAdapter;
import com.company.laeventa.dialogs.AdvanceDialog;
import com.company.laeventa.models.Venue;
import com.company.laeventa.volley.VolleySingleton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {


    protected Context context;
    protected FragmentManager fragmentManager;

    protected LinearLayout progressLayout;
    protected LinearLayout searchContainer;
    protected VenueDetailFragment venueDetailFragment;

    // protected RecyclerView recyclerView;
    protected ListView resultListView;
    protected List<Venue> venueList;

    protected LinearLayout advanceSearch;
    protected LinearLayout sortSearch;

    public ResultFragment() {
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
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        getActivity().findViewById(R.id.favourite).setVisibility(View.GONE);
        getActivity().findViewById(R.id.favourite_progress).setVisibility(View.GONE);

        progressLayout = view.findViewById(R.id.customized_progressbar);
        searchContainer = view.findViewById(R.id.search_container);

        // recyclerView = view.findViewById(R.id.recycler_result);
        resultListView = view.findViewById(R.id.result_listview);

        advanceSearch = view.findViewById(R.id.advance_search_button);
        sortSearch = view.findViewById(R.id.sort_search_button);


        venueList = new ArrayList<Venue>(50);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        feedData();
        setOnClickFunction();
        progressLayout.setVisibility(View.VISIBLE);
        searchContainer.setVisibility(View.GONE);

        fragmentManager = getActivity().getSupportFragmentManager();

        // ResultAdapter resultAdapter = new ResultAdapter(context, venueList);
        //recyclerView.setAdapter(resultAdapter);

        ResultListAdapter resultListAdapter = new ResultListAdapter(context, venueList);

        resultListView.setAdapter(resultListAdapter);

        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                venueDetailFragment = (VenueDetailFragment) fragmentManager.findFragmentByTag("details");
                if (venueDetailFragment == null) {
                    venueDetailFragment = new VenueDetailFragment();
                    fragmentManager.beginTransaction().setCustomAnimations( android.R.anim.fade_in,android.R.anim.fade_out).replace(R.id.fragment_container, venueDetailFragment, "details").addToBackStack("details").commit();
                } else {
                    fragmentManager.beginTransaction().setCustomAnimations( android.R.anim.fade_in,android.R.anim.fade_out).replace(R.id.fragment_container, venueDetailFragment, "details").addToBackStack("details").commit();
                }
            }
        });


        delay();

        Log.i("testing",venueList.toString());
    }

    public void setList(List<Venue> venueList) {
        this.venueList = venueList;
    }

    private void feedData() {

        Venue venue1 = new Venue("Hotel Tripse", "₹1245", "known for its aroma", "230-750", "https://images.unsplash.com/photo-1535827841776-24afc1e255ac?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=675&q=80" ,false , 3.7f);
        venueList.add(venue1);
        Venue venue2 = new Venue("Hotel Tripse", "₹1245", "known for its aroma", "230-750", "https://s-ec.bstatic.com/images/hotel/max1024x768/147/147997361.jpg",false, 3.7f);
        venueList.add(venue2);
        Venue venue3 = new Venue("Hotel Tripse", "₹1245", "known for its aroma", "230-750","https://www.ahstatic.com/photos/7205_ho_00_p_2048x1536.jpg", false, 3.7f);
        venueList.add(venue3);
        Venue venue4 = new Venue("Hotel Tripse", "₹1245", "known for its aroma", "230-750", "https://www3.hilton.com/resources/media/hi/IADMRHF/en_US/img/shared/full_page_image_gallery/main/HH_hotelextdusk_2_675x359_FitToBoxSmallDimensionSmallDimension_Center.jpg" , false, 3.7f);
        venueList.add(venue4);
        Venue venue5 = new Venue("Hotel Tripse", "₹1245", "known for its aroma", "230-750", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-W7iKvRbb-gJyyqG9w_9NR7wXXSUFM-u7k1FaGR6E2xlLUxS_hg", false, 3.7f);
        venueList.add(venue5);

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
                searchContainer.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }



    private void setOnClickFunction() {

        advanceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvanceDialog advanceDialog = (AdvanceDialog) fragmentManager.findFragmentByTag("advance");
                if (advanceDialog == null) {
                    advanceDialog = new AdvanceDialog();
                    advanceDialog.show(fragmentManager, "advance");
                } else {
                    advanceDialog.show(fragmentManager, "advance");
                }
            }
        });


        sortSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "work in progress!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void fetchVenues(){


        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

        String url = "http://104.211.90.144:1410/api/v-1.0/login";

        JSONObject jsonObject = new JSONObject();

        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // on success
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })

        {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };

        requestQueue.add(jsonObjRequest);

    }

}
