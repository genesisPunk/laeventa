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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.company.laeventa.R;
import com.company.laeventa.dialogs.AddImageDialog;
import com.company.laeventa.dialogs.GalleryDialog;
import com.theartofdev.edmodo.cropper.CropImage;
// import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditAreaFragment extends Fragment {

    protected Context context;
    protected FragmentManager fragmentManager;

    protected ArrayList<String> imagesList;
    protected int position = 0;

    protected Button deletePhotos;
    protected Button addPhotos;
    protected Button editButton;

    protected ImageView profileAreaImage;
    protected Uri imageUri;

    protected LinearLayout hourlyLayout;
    protected LinearLayout dailyLayout;

    protected CheckBox hourlyCheckbox;
    protected CheckBox dailyCheckbox;


    public EditAreaFragment() {
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
        View view = inflater.inflate(R.layout.fragment_edit_area, container, false);

        deletePhotos = view.findViewById(R.id.deletePhotosButton);
        addPhotos = view.findViewById(R.id.addPhotosButton);
        editButton = view.findViewById(R.id.editProfileImageButton);
        profileAreaImage = view.findViewById(R.id.area_profile_image);

        hourlyLayout = view.findViewById(R.id.hourlyLayout);
        dailyLayout = view.findViewById(R.id.dailyLayout);

        hourlyCheckbox = view.findViewById(R.id.checkBoxHourly);
        dailyCheckbox = view.findViewById(R.id.checkBoxDaily);

        imagesList = new ArrayList<>(10);
        feedData();

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fragmentManager = getActivity().getSupportFragmentManager();
        setButtons();

    }

    private void setButtons() {

        addPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChooseFile();
            }
        });

        deletePhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GalleryDialog galleryDialog = (GalleryDialog) fragmentManager.findFragmentByTag("gallery");
                if (galleryDialog == null) {
                    galleryDialog = new GalleryDialog();
                    galleryDialog.setData(imagesList, position);
                    galleryDialog.show(fragmentManager, "gallery");
                } else {
                    galleryDialog.show(fragmentManager, "gallery");
                }

            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChooseFile();
            }
        });

        hourlyCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hourlyCheckbox.isChecked()){
                    hourlyLayout.setVisibility(View.VISIBLE);
                }else{
                    hourlyLayout.setVisibility(View.GONE);
                }
            }
        });

        dailyCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dailyCheckbox.isChecked()){
                    dailyLayout.setVisibility(View.VISIBLE);
                }else{
                    dailyLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void onChooseFile() {

        CropImage.activity()
                .start(context, this);
    }

    private void feedData() {

        imagesList.add("https://images.unsplash.com/photo-1551543801-fb7bdeb9fc4a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=334&q=80");
        imagesList.add("https://images.unsplash.com/photo-1503152977911-f125b5741a6d?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");
        imagesList.add("https://images.unsplash.com/photo-1551543732-f0540030c379?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80");
        imagesList.add("https://images.unsplash.com/photo-1496033604106-04799291ee86?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");
        imagesList.add("https://images.unsplash.com/photo-1519684968101-1095455a6d15?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80");
        imagesList.add("https://images.unsplash.com/photo-1503152977911-f125b5741a6d?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                profileAreaImage.setImageURI(imageUri);

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


}
