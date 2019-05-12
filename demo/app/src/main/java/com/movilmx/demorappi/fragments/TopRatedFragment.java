package com.movilmx.demorappi.fragments;


import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movilmx.core.communication.MovieControllerObject;
import com.movilmx.core.ui.GFragment;
import com.movilmx.core.videos.Container;
import com.movilmx.demorappi.R;
import com.movilmx.demorappi.videos.VideosAdapter;

public class TopRatedFragment extends GFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRootView(
                inflater.inflate(
                        R.layout.f_top_rated,
                        container,
                        false));
        assignViews();
        return getRootView();
    }

    @Override
    public void movieControllerEvent(MovieControllerEventType eventType,
                                     MovieControllerObject movieControllerObject) {
        super.movieControllerEvent(eventType, movieControllerObject);
        switch (eventType){
            case TOPRATED:{
                videoContainer = (Container) movieControllerObject.getData();
                drawList();
                break;
            }
        }
    }

    private void drawList(){
        try{
            videosAdapter = new VideosAdapter();
            rvVideos     .setAdapter(videosAdapter);
            videosAdapter.setVideos(videoContainer.getVideos());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void assignViews(){
        Log.d(TAG, "assignViews() called");
        rvVideos = getRootView().findViewById(R.id.rv_videos);
        rvVideos.setHasFixedSize(true);
        rvVideos.setItemViewCacheSize(10);
        getMovieController().requestMovies("1", this);
    }

    private RecyclerView      rvVideos;
    private VideosAdapter     videosAdapter;
    private Container         videoContainer;
}
