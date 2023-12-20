package com.company.laeventa.fragments;


import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.company.laeventa.R;
import com.company.laeventa.dialogs.ChooseSuitedForDialog;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditOverviewFragment extends Fragment {

    Context context;

    protected FragmentManager fragmentManager;
    protected Button suitedForButton;

    protected ImageButton openTimeButton;
    protected ImageButton closeTimeButton;

    protected TextView openTime;
    protected TextView closeTime;

    protected CheckBox hourlyTimeCheckBox;
    protected CheckBox dailyTimeCheckBox;

    protected LinearLayout hourlyLayout;
    protected LinearLayout dailyLayout;

    TimePickerDialog picker;

    public EditOverviewFragment() {
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
        View view = inflater.inflate(R.layout.fragment_edit_overview, container, false);

        fragmentManager = getFragmentManager();
        openTimeButton = view.findViewById(R.id.openTimePickerButton);
        closeTimeButton = view.findViewById(R.id.closeTimePickerButton);
        suitedForButton = view.findViewById(R.id.suitedForButton);
        openTime = view.findViewById(R.id.openTimeTextView);
        closeTime = view.findViewById(R.id.closeTimeTextView);
        hourlyTimeCheckBox = view.findViewById(R.id.checkBoxHourly);
        dailyTimeCheckBox = view.findViewById(R.id.checkBoxDaily);
        hourlyLayout = view.findViewById(R.id.hourlyLayout);
        dailyLayout = view.findViewById(R.id.dailyLayout);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setButtons();
    }

    private void setButtons() {

        suitedForButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseSuitedForDialog chooseSuitedForDialog = (ChooseSuitedForDialog) fragmentManager.findFragmentByTag("suited");
                if (chooseSuitedForDialog == null) {
                    chooseSuitedForDialog = new ChooseSuitedForDialog();
                    chooseSuitedForDialog.show(fragmentManager, "suited");
                } else {
                    chooseSuitedForDialog.show(fragmentManager, "suited");
                }
            }
        });

        openTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                openTime.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });


        closeTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cldr1 = Calendar.getInstance();
                int hour = cldr1.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr1.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                closeTime.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });


        hourlyTimeCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( hourlyTimeCheckBox.isChecked()){
                        hourlyLayout.setVisibility(View.VISIBLE);
                      //  hourlyLayout.setAnimation(android.R.anim.slide_in_left);
                      //  hourlyLayout.animate();
                }else{
                        hourlyLayout.setVisibility(View.GONE);
                }
            }
        });

        dailyTimeCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( dailyTimeCheckBox.isChecked()){
                        dailyLayout.setVisibility(View.VISIBLE);
                }else{
                        dailyLayout.setVisibility(View.GONE);
                }
            }
        });
    }
}
