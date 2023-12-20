package com.company.laeventa.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.company.laeventa.ProfileActivity;
import com.company.laeventa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    protected Context context;
    protected FragmentManager fragmentManager;

    protected TextView loginButton;

    protected Button signupButton;

    LinearLayout progressBar;
    LinearLayout verified;
    LinearLayout fragmentsContainer;

    public LoginFragment() {
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();

        loginButton = view.findViewById(R.id.login_button);

        signupButton = view.findViewById(R.id.signup_button);

        progressBar = getActivity().findViewById(R.id.customized_progressbar);
        fragmentsContainer = getActivity().findViewById(R.id.fragments_container);
        verified = getActivity().findViewById(R.id.verified_layout);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifyCredentials();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSignupFragment();
            }
        });
    }

    private void openSignupFragment() {

        SignUpFragment signUpFragment = (SignUpFragment) getActivity().getSupportFragmentManager().findFragmentByTag("signup");

        if (!(signUpFragment == null)) {
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.slide_in_left,android.R.anim.fade_out).replace(R.id.fragments_container, signUpFragment, "signup").addToBackStack("signup").commit();
        } else {
            signUpFragment = new SignUpFragment();
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations( android.R.anim.fade_in,android.R.anim.fade_out).replace(R.id.fragments_container, signUpFragment, "signup").addToBackStack("signup").commit();
        }

    }

    private void verifyCredentials() {

        //start progress bar and hiding fragment layout
        progressBar.setVisibility(View.VISIBLE);
        fragmentsContainer.setVisibility(View.GONE);

        delay();
    }

    private void openProfileActivity() {

        progressBar.setVisibility(View.GONE);
        verified.setVisibility(View.VISIBLE);

        getFragmentManager().popBackStack();
        Intent myIntent = new Intent(context, ProfileActivity.class);
        context.startActivity(myIntent);
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
                openProfileActivity();
            }
        }, 2000);
    }

}
