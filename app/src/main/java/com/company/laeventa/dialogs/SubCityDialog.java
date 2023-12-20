package com.company.laeventa.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.company.laeventa.R;
import com.company.laeventa.fragments.SearchFragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SubCityDialog extends AppCompatDialogFragment {


    protected Context context;

    protected String city;
    protected ArrayList<String> selectedRegions;

    protected Button okayButton;
    protected Button cancelButton;


    public SubCityDialog() {
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

        View view = inflater.inflate(R.layout.dialog_sub_city, null);

        selectedRegions = new ArrayList<>(10);
        selectedRegions.clear();

        setVariables(view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        AlertDialog dialog = builder.create();
        return dialog;
    }

    private void setVariables(View view) {
        okayButton = view.findViewById(R.id.okayButtonDialog);
        cancelButton = view.findViewById(R.id.discardButtonDialog);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setButtons();

        selectedRegions.add("Ravet");
        selectedRegions.add("Hinjewadi");
        selectedRegions.add("Wakad");
        selectedRegions.add("Baner");
        selectedRegions.add("Kalysni nagar");
        selectedRegions.add("Pashan");
        selectedRegions.add("Chinchwad");
        selectedRegions.add("Pimpri");

    }

    private void setButtons() {

        okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSelection();
                dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void checkSelection() {

        SearchFragment parent = (SearchFragment) getTargetFragment();
        parent.setRegionsTextview(selectedRegions);
    }


    public void setCity(String city) {
        this.city = city;
    }
}
