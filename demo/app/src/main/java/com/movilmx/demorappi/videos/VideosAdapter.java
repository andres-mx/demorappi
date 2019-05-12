package com.movilmx.demorappi.videos;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.movilmx.core.videos.Videos;
import com.movilmx.demorappi.R;

import java.util.ArrayList;

public class VideosAdapter extends RecyclerView.Adapter<VideosHolder> {

    public VideosAdapter(){

    }

    @NonNull
    @Override
    public VideosHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new VideosHolder(
                LayoutInflater
                        .from(viewGroup.getContext())
                        .inflate(R.layout.item_video, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideosHolder videosHolder, int position) {
        videosHolder.bind(videos.get(position));
    }

    @Override
    public int getItemCount() {
        if (null != videos){
            return videos.size();
        }
        return 0;
    }

    private ArrayList<Videos> videos;

    public void setVideos(ArrayList<Videos> videos) {
        this.videos = videos;
        notifyDataSetChanged();
    }

    public ArrayList<Videos> getVideos() {
        return videos;
    }
}