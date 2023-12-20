package com.company.laeventa.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.company.laeventa.R;
import com.company.laeventa.dialogs.AddImageDialog;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditVenueDetailsFragment extends Fragment {

    protected Context context;
    protected FragmentManager fragmentManager;

    protected Button editProfile;
    protected Button editImage1;
    protected Button editImage2;
    protected Button editImage3;
    protected Button editImage4;

    protected ImageView profileImage;
    protected ImageView imageView1;
    protected ImageView imageView2;
    protected ImageView imageView3;
    protected ImageView imageView4;

    protected ArrayList<String> imagesList;
    protected Uri imageUri;


    public EditVenueDetailsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_edit_venue_details, container, false);

        setView(view);

        imagesList = new ArrayList<>(10);
        feedData();

        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fragmentManager = getActivity().getSupportFragmentManager();
        setButtonOnClick();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                profileImage.setImageURI(imageUri);

                AddImageDialog addImageDialog = (AddImageDialog) fragmentManager.findFragmentByTag("preview");
                if (addImageDialog == null) {
                    addImageDialog = new AddImageDialog();
                    addImageDialog.setData(imageUri);
                    addImageDialog.show(fragmentManager, "preview");
                } else {
                    addImageDialog.show(fragmentManager, "preview");
                }

            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception e = result.getError();
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setButtonOnClick() {

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChooseFile();
            }
        });

    }

    private void onChooseFile() {

        CropImage.activity()
                .start(context, this);
    }

    private void setView(View view) {

        editProfile = view.findViewById(R.id.editProfileImageButton);
        editImage1 = view.findViewById(R.id.editImage1Button);
        editImage2 = view.findViewById(R.id.editImage2Button);
        editImage3 = view.findViewById(R.id.editImage3Button);
        editImage4 = view.findViewById(R.id.editImage4Button);

        profileImage = view.findViewById(R.id.venue_profile_image);
        imageView1 = view.findViewById(R.id.venueImage1);
        imageView2 = view.findViewById(R.id.venueImage2);
        imageView3 = view.findViewById(R.id.venueImage3);
        imageView4 = view.findViewById(R.id.venueImage4);

    }

    private void feedData() {
        imagesList.add("https://images.unsplash.com/photo-1551543801-fb7bdeb9fc4a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=334&q=80");
        imagesList.add("https://images.unsplash.com/photo-1503152977911-f125b5741a6d?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");
        imagesList.add("https://images.unsplash.com/photo-1551543732-f0540030c379?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80");
        imagesList.add("https://images.unsplash.com/photo-1496033604106-04799291ee86?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");
        imagesList.add("https://images.unsplash.com/photo-1519684968101-1095455a6d15?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80");

    }
}
