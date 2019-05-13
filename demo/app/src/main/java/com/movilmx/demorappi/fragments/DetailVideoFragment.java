package com.movilmx.demorappi.fragments;


import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.movilmx.core.communication.MovieControllerObject;
import com.movilmx.core.constants.Constants;
import com.movilmx.core.ui.GFragment;
import com.movilmx.core.videos.Videos;
import com.movilmx.demorappi.R;
import com.squareup.picasso.Picasso;

public class DetailVideoFragment extends GFragment {

    public static DetailVideoFragment newInstance(String videoId, Videos video) {
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
        drawList();
        return getRootView();
    }

    @Override
    public void movieControllerEvent(MovieControllerEventType eventType,
                                     MovieControllerObject movieControllerObject) {
        super.movieControllerEvent(eventType, movieControllerObject);
        switch (eventType) {
            case DETAILVIDEO: {
                Log.d(TAG, "movieControllerEvent() called with: eventType = [" + eventType + "], movieControllerObject = [" + movieControllerObject.getData() + "]");
                break;
            }
        }
    }

    private void drawList() {
        try {
            Picasso.with(getContext())
                    .load(Constants.getUrlImage(video.getImage()))
                    .into(ivVideos);
            tvTitle.setText(video.getTitle());
            tvDescription.setText(video.getDescription());
            tvVoteCount.setText(tvVoteCount.getText().toString()
                    + String.format("%.0f", video.getVoteCount()));
            tvVoteAverage.setText(String.format(
                    "%s%s", tvVoteAverage.getText().toString(), video.getVoteAverage()));
            tvPopularity.setText(tvPopularity.getText().toString()
                    + String.format("%.0f", video.getPopularity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void assignViews() {
        ivVideos      = getRootView().findViewById(R.id.iv_video);
        tvTitle       = getRootView().findViewById(R.id.tv_title);
        tvDescription = getRootView().findViewById(R.id.tv_description);
        tvVoteCount   = getRootView().findViewById(R.id.tv_vote_count);
        tvVoteAverage = getRootView().findViewById(R.id.tv_vote_average);
        tvPopularity  = getRootView().findViewById(R.id.tv_popularity);
//        getMovieController().requestDetailVideo(videoId, this);
    }

    private String    videoId;
    private Videos    video;
    private ImageView ivVideos;
    private TextView  tvTitle;
    private TextView  tvDescription;
    private TextView  tvVoteCount;
    private TextView  tvVoteAverage;
    private TextView  tvPopularity;
}