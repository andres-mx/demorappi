package com.movilmx.demorappi.fragments;


import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movilmx.core.communication.MovieControllerObject;
import com.movilmx.core.ui.GFragment;
import com.movilmx.core.ui.UIEventType;
import com.movilmx.core.ui.UIObject;
import com.movilmx.core.videos.Container;
import com.movilmx.demorappi.R;
import com.movilmx.demorappi.videos.VideosAdapter;

public class PopularFragment extends GFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRootView(
                inflater.inflate(
                                R.layout.f_popular,
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
            case POPULAR:{
                videoContainer = (Container) movieControllerObject.getData();
                pageNumber     = videoContainer.getPageNumber();
                drawList();
                break;
            }
        }
    }

    @Override
    public void event(UIEventType eventType, UIObject uiObject) {
        super.event(eventType, uiObject);
        switch (eventType){
            case HOLDERCLICK:{
                int id = uiObject.getHolderPosition();
                detailVideo(String.valueOf(videoContainer.getVideos().get(id).getId()),
                        videoContainer.getVideos().get(id));
                break;
            }
        }
    }

    private void drawList(){
        try{
            videosAdapter = new VideosAdapter(this);
            rvVideos     .setAdapter(videosAdapter);
            videosAdapter.setVideos(videoContainer.getVideos());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void assignViews(){
        rvVideos = getRootView().findViewById(R.id.rv_videos);
        rvVideos.setHasFixedSize(true);
        rvVideos.setItemViewCacheSize(10);
        getMovieController().requestPopular(String.valueOf(pageNumber), this);
    }

    private RecyclerView      rvVideos;
    private VideosAdapter     videosAdapter;
    private Container         videoContainer;
    private int               pageNumber = 1;
}