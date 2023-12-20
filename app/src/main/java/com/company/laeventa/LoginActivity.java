package com.company.laeventa;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.company.laeventa.fragments.LoginFragment;
import com.company.laeventa.fragments.SearchFragment;

import static android.view.View.GONE;

public class LoginActivity extends AppCompatActivity {

    protected FragmentManager fragmentManager;
    protected SearchFragment searchFragment;
    protected LoginFragment loginFragment;

    protected LinearLayout fragmentContainer;
    private LinearLayout progressBar;
    private LinearLayout verified;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentContainer = findViewById(R.id.fragments_container);

        progressBar = findViewById(R.id.customized_progressbar);
        verified = findViewById(R.id.verified_layout);

        searchFragment = new SearchFragment();
        loginFragment = new LoginFragment();

        setDefaultFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        verified.setVisibility(GONE);
        fragmentContainer.setVisibility(View.VISIBLE);
    }

    private void setDefaultFragment() {


        //setup visibility of the frames
        progressBar.setVisibility(GONE);
        verified.setVisibility(GONE);
        fragmentContainer.setVisibility(View.VISIBLE);


        //setting fragment manager in global variable
        fragmentManager = getSupportFragmentManager();

        //setting transaction for fragment manager
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragments_container, searchFragment, "search");
        transaction.commit();
    }

}
