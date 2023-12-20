package com.company.laeventa.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.company.laeventa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    protected FragmentManager fragmentManager;

    protected LinearLayout sendOtpLayout;
    protected LinearLayout progressLayout;
    protected LinearLayout verifyOtpLayout;
    protected LinearLayout setPasswordLayout;
    protected LinearLayout successPasswordLayout;

    protected TextView sendOtpButton;
    protected TextView verifyButton;
    protected TextView progressTextview;
    protected TextView setPasswordButton;


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();

        sendOtpLayout =view.findViewById(R.id.send_otp_layout);
        progressLayout = view.findViewById(R.id.customized_progressbar_signup);
        verifyOtpLayout = view.findViewById(R.id.verify_otp_layout);
        setPasswordLayout = view.findViewById(R.id.set_password_layout);
        successPasswordLayout = view.findViewById(R.id.success_password_layout);

        sendOtpButton = view.findViewById(R.id.send_otp_button);
        verifyButton = view.findViewById(R.id.verify_otp_button);
        progressTextview = view.findViewById(R.id.progress_message);
        setPasswordButton = view.findViewById(R.id.set_password_button);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setButtons();
    }

    private void setButtons() {

        sendOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOtpLayout.setVisibility(View.GONE);
                progressLayout.setVisibility(View.VISIBLE);
                sendOtp();
                delay();
            }
        });

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOtpLayout.setVisibility(View.GONE);
                progressLayout.setVisibility(View.VISIBLE);
                progressTextview.setText("Verifying OTP...");
                delay2();
            }
        });

        setPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPasswordLayout.setVisibility(View.GONE);
                progressLayout.setVisibility(View.VISIBLE);
                progressTextview.setText("setting Password...");
                delay3();
            }
        });
    }

    private void sendOtp() {
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
                verifyOtpLayout.setVisibility(View.VISIBLE);
            }
        }, 1300);
    }

    private void delay2() {
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                progressLayout.setVisibility(View.GONE);
                setPasswordLayout.setVisibility(View.VISIBLE);
            }
        }, 1300);
    }

    private void delay3() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                progressLayout.setVisibility(View.GONE);
                successPasswordLayout.setVisibility(View.VISIBLE);
                delay4();
            }
        }, 1300);
    }

    private void delay4() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                successPasswordLayout.setVisibility(View.GONE);
                fragmentManager.popBackStack();
            }
        }, 1400);
    }
}
