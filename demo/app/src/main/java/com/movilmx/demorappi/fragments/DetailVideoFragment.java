package com.movilmx.demorappi.fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movilmx.core.communication.MovieControllerObject;
import com.movilmx.core.ui.GFragment;
import com.movilmx.core.videos.Videos;
import com.movilmx.demorappi.R;

public class DetailVideoFragment extends GFragment {

    public static DetailVideoFragment newInstance(int videoId, Videos video) {
        DetailVideoFragment fragment = new DetailVideoFragment();
        fragment.videoId = videoId;
        fragment.video   = video;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRootView(
                inflater.inflate(
                        R.layout.f_detail_video,
                        container,
                        false));
        assignViews();
        return getRootView();
    }

    @Override
    public void movieControllerEvent(MovieControllerEventType eventType,
                                     MovieControllerObject movieControllerObject) {
        super.movieControllerEvent(eventType, movieControllerObject);
        switch (eventType) {
            case POPULAR: {
                drawList();
                break;
            }
        }
    }

    private void drawList() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void assignViews() {

    }

    private int    videoId;
    private Videos video;

}
