package com.company.laeventa.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.company.laeventa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditPoliciesFragment extends Fragment {


    protected Context context;

    protected LinearLayout advanceLayout;
    protected LinearLayout cancellationLayout;

    protected CheckBox checkboxAdvance;
    protected CheckBox checkboxCancellation;

    public EditPoliciesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_edit_policies, container, false);

        advanceLayout = view.findViewById(R.id.advancePaymentLayout);
        cancellationLayout = view.findViewById(R.id.cancellationPaymentLayout);

        checkboxAdvance = view.findViewById(R.id.checkBoxAdvance);
        checkboxCancellation = view.findViewById(R.id.checkboxCancellation);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setButtons();
    }

    private void setButtons() {

        checkboxAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkboxAdvance.isChecked()){
                    advanceLayout.setVisibility(View.VISIBLE);
                }else{
                    advanceLayout.setVisibility(View.GONE);
                }
            }
        });


        checkboxCancellation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkboxCancellation.isChecked()){
                   cancellationLayout.setVisibility(View.VISIBLE);
                }else{
                    cancellationLayout.setVisibility(View.GONE);
                }
            }
        });
    }
}
