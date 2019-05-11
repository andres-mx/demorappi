package com.movilmx.demorappi.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movilmx.demorappi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpComingFragment extends Fragment {


    public UpComingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.f_up_coming, container, false);
    }

}
