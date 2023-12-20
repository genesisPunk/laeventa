package com.company.laeventa.dialogs;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.company.laeventa.R;

import java.util.ArrayList;
import java.util.List;


public class AdvanceDialog extends AppCompatDialogFragment {


    protected Context context;

    protected Spinner citySpinner;

    public AdvanceDialog() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_advance_dialog, null);

        citySpinner = view.findViewById(R.id.city_spinner);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List categories = new ArrayList(5);
        categories.add("Select a City!");
        categories.add("Pune");
        categories.add("Mumbai");


        citySpinner.setPrompt("Select your favorite Planet!");

        // Creating adapter for spinner
        ArrayAdapter dataAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        citySpinner.setAdapter(dataAdapter);

    }
}
