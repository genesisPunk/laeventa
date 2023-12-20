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

import com.company.laeventa.MyApplication;
import com.company.laeventa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PoliciesFragment extends Fragment {


    protected Context context;
    protected FragmentManager fragmentManager;

    protected Boolean isAdmin = true;

    protected ImageView policiesEditButton;



    public PoliciesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_policies, container, false);

        isAdmin = MyApplication.getIsAdmin();

        policiesEditButton = view.findViewById(R.id.venue_policies_edit);

        if (isAdmin){
            policiesEditButton.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fragmentManager = getActivity().getSupportFragmentManager();

        setButtons();
    }

    private void setButtons() {

        policiesEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditPoliciesFragment editPoliciesFragment = (EditPoliciesFragment) fragmentManager.findFragmentByTag("editPolicies");

                if (editPoliciesFragment == null) {
                    editPoliciesFragment = new EditPoliciesFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, editPoliciesFragment, "editPolicies").addToBackStack("editPolicies").commit();
                } else {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, editPoliciesFragment, "editPolicies").addToBackStack("editPolicies").commit();
                }
            }
        });
    }
}
