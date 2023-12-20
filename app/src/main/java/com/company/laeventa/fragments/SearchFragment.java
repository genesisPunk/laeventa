package com.company.laeventa.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.company.laeventa.MyApplication;
import com.company.laeventa.dialogs.SportsEventDialog;
import com.company.laeventa.dialogs.SubCityDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.company.laeventa.R;
import com.company.laeventa.VenueActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    Context context;

    FragmentManager fragmentManager;

    FloatingActionButton loginButton;
    LinearLayout fab1;
    LinearLayout fab2;
    LinearLayout fab3;
    LinearLayout screenLayout;

    protected ImageButton editRegionsButton;
    protected ImageButton editSportsButton;

    LinearLayout subcityLayout;
    LinearLayout subcityList;
    LinearLayout sportsLayout;
    LinearLayout sportsList;

    Spinner citySpinner;
    Spinner eventSpinner;

    LoginFragment loginFragment;

    TextView searchButton;

    boolean isFABOpen = false;

    public SearchFragment() {
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        loginButton = view.findViewById(R.id.login_floating_button);
        fab1 = view.findViewById(R.id.fab1);
        fab2 = view.findViewById(R.id.fab2);
        fab3 = view.findViewById(R.id.fab3);
        screenLayout = view.findViewById(R.id.screen_fragment);
        searchButton = view.findViewById(R.id.search_button);

        citySpinner = view.findViewById(R.id.spinner_city_search);
        eventSpinner = view.findViewById(R.id.spinner_event_search);

        editRegionsButton = view.findViewById(R.id.editRegionsButton);
        editSportsButton = view.findViewById(R.id.editSportsButton);
        subcityList = view.findViewById(R.id.subcityList);
        subcityLayout = view.findViewById(R.id.subcity_layout);
        subcityLayout.setVisibility(View.GONE);
        sportsList = view.findViewById(R.id.sportsList);
        sportsLayout = view.findViewById(R.id.sports_layout);
        sportsLayout.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fab1.setVisibility(View.GONE);
        fab2.setVisibility(View.GONE);
        fab3.setVisibility(View.GONE);

        fragmentManager = getActivity().getSupportFragmentManager();

        setSpinners();
        citySpinner.setSelection(0);
        eventSpinner.setSelection(0);
        setOnClick();

    }

    private void setOnClick() {

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyApplication.setIsAdmin(false);
                closeFABMenu();
                Intent myIntent = new Intent(context, VenueActivity.class);
                context.startActivity(myIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Work under progress!", Toast.LENGTH_SHORT).show();
                closeFABMenu();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFABMenu();
                openLoginFragment();
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Work under progress!", Toast.LENGTH_SHORT).show();
                closeFABMenu();
            }
        });

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).toString().equals("City?")) {
                    subcityLayout.setVisibility(View.GONE);
                } else if (parent.getItemAtPosition(position).toString().toLowerCase().equals("pune")) {
                    openSubcityDialog("pune");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        eventSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).toString().equals("Sports")) {
                    openSportsDialog();
                } else {
                    sportsLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        editRegionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSubcityDialog(citySpinner.getSelectedItem().toString());
            }
        });

        editSportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSportsDialog();
            }
        });

    }

    private void setSpinners() {

        List cityList = new ArrayList(5);
        cityList.add("City?");
        cityList.add("Pune");
        cityList.add("Mumbai");

        List eventList = new ArrayList(5);
        eventList.add("Event?");
        eventList.add("Party");
        eventList.add("Sports");
        eventList.add("Marriage");
        eventList.add("Concert");
        eventList.add("Conference");
        eventList.add("Meeting");


        // Creating adapter for spinner
        ArrayAdapter cityAdapter = new ArrayAdapter(context, R.layout.spinner_city_main_item, cityList);
        ArrayAdapter eventAdapter = new ArrayAdapter(context,  R.layout.spinner_city_main_item, eventList);

        // Drop down layout style - list view with radio button
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        citySpinner.setAdapter(cityAdapter);
        eventSpinner.setAdapter(eventAdapter);

    }

    private void openSubcityDialog(String city) {
        SubCityDialog subCityDialog = (SubCityDialog) fragmentManager.findFragmentByTag("subcity");
        if (subCityDialog == null) {
            subCityDialog = new SubCityDialog();
            subCityDialog.setCity(city);
            subCityDialog.setCancelable(false);
            subCityDialog.setTargetFragment(SearchFragment.this, 1337);
            subCityDialog.show(fragmentManager, "subcity");
        } else {
            citySpinner.setSelection(0);
            subCityDialog.dismiss();
//            subCityDialog.setCity(city);
//            subCityDialog.setCancelable(false);
//            subCityDialog.setTargetFragment(SearchFragment.this, 1337);
//            subCityDialog.show(fragmentManager, "subcity");

        }
    }

    private void openSportsDialog() {
        SportsEventDialog sportsEventDialog = (SportsEventDialog) fragmentManager.findFragmentByTag("sports");
        if (sportsEventDialog == null) {
            sportsEventDialog = new SportsEventDialog();
            sportsEventDialog.setCancelable(false);
            sportsEventDialog.setTargetFragment(SearchFragment.this, 1336);
            sportsEventDialog.show(fragmentManager, "sports");
        }else {
            eventSpinner.setSelection(0);
            sportsEventDialog.dismiss();
        }
    }

    private void openLoginFragment() {

        loginFragment = (LoginFragment) getActivity().getSupportFragmentManager().findFragmentByTag("login");

        if (!(loginFragment == null)) {
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).replace(R.id.fragments_container, loginFragment, "login").addToBackStack("login").commit();
        } else {
            loginFragment = new LoginFragment();
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).replace(R.id.fragments_container, loginFragment, "login").addToBackStack("login").commit();
        }

    }

    private void showFABMenu() {
        isFABOpen = true;
        fab1.setVisibility(View.VISIBLE);
        fab2.setVisibility(View.VISIBLE);
        fab3.setVisibility(View.VISIBLE);
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
    }

    private void closeFABMenu() {
        isFABOpen = false;
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
        fab3.animate().translationY(0);
        fab1.setVisibility(View.GONE);
        fab2.setVisibility(View.GONE);
        fab3.setVisibility(View.GONE);
    }

    public void setRegionsTextview(ArrayList<String> regions) {


        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < regions.size(); i++) {
            lparams.setMargins(3, 0, 3, 0);
            TextView tv = new TextView(context);

            tv.setLayoutParams(lparams);
            tv.setPadding(5, 3, 5, 3);

            tv.setText(regions.get(i));
            tv.setBackgroundResource(R.drawable.rectangle);
            tv.setTextColor(getResources().getColor(R.color.white));
            subcityList.addView(tv);

        }

        subcityLayout.setVisibility(View.VISIBLE);
    }

    public void setSportsTextview(ArrayList<String> sports) {

        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < sports.size(); i++) {
            lparams.setMargins(3, 0, 3, 0);
            TextView tv = new TextView(context);

            tv.setLayoutParams(lparams);
            tv.setPadding(5, 3, 5, 3);

            tv.setText(sports.get(i));
            tv.setBackgroundResource(R.drawable.rectangle);
            tv.setTextColor(getResources().getColor(R.color.white));
            sportsList.addView(tv);

        }

        sportsLayout.setVisibility(View.VISIBLE);
    }
}
