package com.company.laeventa.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.company.laeventa.fragments.SportsPolicyFragment;
import com.company.laeventa.fragments.AreasFragment;
import com.company.laeventa.fragments.OverviewDetailsFragment;
import com.company.laeventa.fragments.PoliciesFragment;

public class DetailsPagerAdapter extends FragmentStatePagerAdapter {

    Context context;

    FragmentManager fragmentManager;
    int noOfTabs;
    boolean isSports;

    public DetailsPagerAdapter(Context context, FragmentManager fm, int noOfTabs, boolean isSports) {
        super(fm);
        this.context = context;
        fragmentManager = fm;
        this.noOfTabs = noOfTabs;
        this.isSports = isSports;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                OverviewDetailsFragment overviewDetailsFragment = new OverviewDetailsFragment();
                return overviewDetailsFragment;

            case 1:
                AreasFragment areasFragment = new AreasFragment();
                return areasFragment;

            case 2:

                if (isSports){
                    SportsPolicyFragment sportsPolicyFragment = new SportsPolicyFragment();
                    return sportsPolicyFragment;
                }else{
                    PoliciesFragment policiesFragment = new PoliciesFragment();
                    return policiesFragment;
                }

//            case 3:
//                ImagesFragment imagesFragment = new ImagesFragment();
//                return imagesFragment;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Overview";
            case 1:
                return "Areas";
            case 2:
                return "Policies";
//            case 3:
//                return "Photos";
            default:
                return null;
        }
    }
}
