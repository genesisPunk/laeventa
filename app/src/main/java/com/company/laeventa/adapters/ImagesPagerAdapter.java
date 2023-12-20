package com.company.laeventa.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.laeventa.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImagesPagerAdapter extends PagerAdapter {


    protected Context context;

    protected ArrayList<String> imagesList;

    protected TextView imageNumber;

    public ImagesPagerAdapter(Context context, ArrayList<String> imagesList, TextView textViewImageCount) {
        this.context = context;
        this.imagesList = imagesList;
        this.imageNumber = textViewImageCount;
    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.get().load(imagesList.get(position)).error( R.drawable.ic_error_loading )
                .placeholder( R.drawable.progress_animation_picasso ).into(imageView);
        container.addView(imageView,0);

        imageNumber.setText(Integer.toString(position+1)+ " / "+ Integer.toString(getCount()));

        return imageView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
