package com.company.laeventa.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.company.laeventa.R;

public class AddImageDialog extends AppCompatDialogFragment {

    protected Context context;

    protected Uri imageUri;

    protected ImageView image;
    protected Button addButton;
    protected Button discardButton;

    protected LinearLayout imageLayout;
    protected LinearLayout progressLayout;
    protected LinearLayout successLayout;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_image, null);

        image = view.findViewById(R.id.addDialogImage);
        addButton = view.findViewById(R.id.addButtonDialog);
        discardButton = view.findViewById(R.id.discardButtonDialog);
        imageLayout = view.findViewById(R.id.imageLayout);
        successLayout = view.findViewById(R.id.success_layout);
        progressLayout = view.findViewById(R.id.progressLayout);

        imageLayout.setVisibility(View.VISIBLE);
        successLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.GONE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    public void setData(Uri imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setButtons();
        image.setImageURI(imageUri);
    }

    private void setButtons() {

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageLayout.setVisibility(View.GONE);
                successLayout.setVisibility(View.GONE);
                progressLayout.setVisibility(View.VISIBLE);
                delay();
            }
        });

        discardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void delay() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                imageLayout.setVisibility(View.GONE);
                successLayout.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);
                delay1();
            }
        }, 2000);
    }

    private void delay1() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                dismiss();
            }
        }, 500);
    }



}
