package com.company.laeventa.dialogs;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.company.laeventa.R;
import com.company.laeventa.fragments.SearchFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportsEventDialog extends AppCompatDialogFragment {

    protected Context context;

    protected ArrayList<String> selectedSports;

    protected Button okayButton;
    protected Button cancelButton;


    public SportsEventDialog() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.sports_event_dialog, null);

        selectedSports = new ArrayList<>(10);
        selectedSports.clear();

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

        selectedSports.add("Basketball");
        selectedSports.add("VolleyBall");
        selectedSports.add("Badminton");

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
        parent.setSportsTextview(selectedSports);
    }
}
