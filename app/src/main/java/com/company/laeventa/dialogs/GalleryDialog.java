package com.company.laeventa.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.company.laeventa.R;
import com.company.laeventa.adapters.ImagesPagerAdapter;

import java.util.ArrayList;

public class GalleryDialog extends AppCompatDialogFragment {

    protected Context context;

    protected ArrayList<String> imagesList;
    protected int position;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_gallery_dialog, null);

        TextView textViewImageCount = view.findViewById(R.id.textview_image_count);

        ViewPager viewPager = view.findViewById(R.id.view_pager_gallery);
        ImagesPagerAdapter imagesPagerAdapter = new ImagesPagerAdapter(getContext(), imagesList, textViewImageCount);
        viewPager.setAdapter(imagesPagerAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void setData(ArrayList<String> imagesList, int position) {
        this.imagesList = imagesList;
        this.position = position;
    }
}
