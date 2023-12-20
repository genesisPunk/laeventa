package com.company.laeventa.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.company.laeventa.MyApplication;
import com.company.laeventa.R;
import com.company.laeventa.dialogs.GalleryDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AreaDetailsFragment extends Fragment {


    protected Context context;
    protected FragmentManager fragmentManager;

    protected LinearLayout galleryLayout;

    protected ArrayList<String> imagesList;

    protected ImageView editBasic;

    protected ImageView areaProfile;
    protected ImageView imageView1;
    protected ImageView imageView2;
    protected ImageView imageView3;
    protected ImageView imageView4;
    protected ImageView imageView5;
    protected ImageView imageView6;


    protected Boolean isAdmin;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public AreaDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_area_details, container, false);

        isAdmin = MyApplication.getIsAdmin();

        areaProfile = view.findViewById(R.id.area_profile_imageview);

        editBasic = view.findViewById(R.id.area_basic_edit);

        galleryLayout = view.findViewById(R.id.photos_layout);

        imageView1 = view.findViewById(R.id.photos1);
        imageView2 = view.findViewById(R.id.photos2);
        imageView3 = view.findViewById(R.id.photos3);
        imageView4 = view.findViewById(R.id.photos4);
        imageView5 = view.findViewById(R.id.photos5);
        imageView6 = view.findViewById(R.id.photos6);


        if (isAdmin) {
            editBasic.setVisibility(View.VISIBLE);
        }

        imagesList = new ArrayList<>(10);
        feedData();

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fragmentManager = getActivity().getSupportFragmentManager();

        setEditButton();
        setHorizontalGallery();


    }

    private void setHorizontalGallery() {

        Picasso.get().load((imagesList.get(0))).error( R.drawable.ic_error_loading )
                .placeholder( R.drawable.progress_animation_picasso ).into(imageView1);
        Picasso.get().load((imagesList.get(1))).error( R.drawable.ic_error_loading )
                .placeholder( R.drawable.progress_animation_picasso ).into(imageView2);
        Picasso.get().load((imagesList.get(2))).error( R.drawable.ic_error_loading )
                .placeholder( R.drawable.progress_animation_picasso ).into(imageView3);
        Picasso.get().load((imagesList.get(3))).error( R.drawable.ic_error_loading )
                .placeholder( R.drawable.progress_animation_picasso ).into(imageView4);
        Picasso.get().load((imagesList.get(4))).error( R.drawable.ic_error_loading )
                .placeholder( R.drawable.progress_animation_picasso ).into(imageView5);


        galleryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryDialog galleryDialog = (GalleryDialog) fragmentManager.findFragmentByTag("gallery");
                if (galleryDialog == null) {
                    galleryDialog = new GalleryDialog();
                    galleryDialog.setData(imagesList,0);
                    galleryDialog.show(fragmentManager, "gallery");
                } else {
                    galleryDialog.show(fragmentManager, "gallery");
                }
            }
        });


    }

    private void setEditButton() {

        editBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditAreaFragment editAreaFragment = (EditAreaFragment) fragmentManager.findFragmentByTag("editArea");

                if (editAreaFragment == null) {
                    editAreaFragment = new EditAreaFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, editAreaFragment, "editArea").addToBackStack("editArea").commit();
                } else {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, editAreaFragment, "editAreae").addToBackStack("editArea").commit();
                }
            }
        });
    }


    private void feedData() {

        imagesList.add("data:image/jpeg;base64,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");
        imagesList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQiKwempEmC5VrSarJbG6HPhXy6tMpAsZK8zArIY-0gqKMlKw0");
        imagesList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT84HffT6WrdvIZGA6B44Q2h_v1vn41FwK3dSYXkn3g171g9aj4");
        imagesList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUnKi4kd-uEgvQXNsIFDm6O7uwiJvBamU0Mt_zUcSAsJi5p1M-");
        imagesList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRam-3GBGnvKOt9htzErMtiCs2OOcxTA5AoO5vno30qvTMzH_EN");

        //Basketball court
//        imagesList.add("https://images.unsplash.com/photo-1551543801-fb7bdeb9fc4a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=334&q=80");
//        imagesList.add("https://images.unsplash.com/photo-1503152977911-f125b5741a6d?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");
//        imagesList.add("https://images.unsplash.com/photo-1551543732-f0540030c379?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80");
//        imagesList.add("https://images.unsplash.com/photo-1496033604106-04799291ee86?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");
//        imagesList.add("https://images.unsplash.com/photo-1519684968101-1095455a6d15?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80");
//        imagesList.add("https://images.unsplash.com/photo-1503152977911-f125b5741a6d?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");


    }
}
