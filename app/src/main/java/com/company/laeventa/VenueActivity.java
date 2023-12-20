package com.company.laeventa;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.company.laeventa.fragments.ResultFragment;
import com.company.laeventa.models.Venue;

import java.util.ArrayList;
import java.util.List;

public class VenueActivity extends AppCompatActivity {


    protected FragmentManager fragmentManager;
    protected ResultFragment resultFragment;


    protected List<Venue> venueList = new ArrayList<>(50);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);

        //setting fragment manager in global variable
        fragmentManager = getSupportFragmentManager();

        clearFragmentManager();

        resultFragment = new ResultFragment();
        // createList();
        // resultFragment.setList(venueList);

        setDefaultFragment();


    }

    private void clearFragmentManager() {

        for (Fragment fragment:getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }


    private void setDefaultFragment() {

        //setting fragment manager in global variable
        fragmentManager = getSupportFragmentManager();

        //setting transaction for fragment manager
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, resultFragment, "result");
        transaction.commit();
    }



}
