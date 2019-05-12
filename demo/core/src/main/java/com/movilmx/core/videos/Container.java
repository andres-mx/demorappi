package com.movilmx.core.videos;

import java.util.ArrayList;

public class Container {
    /**
     * Arregló de videos despúes de la limpieza del consumo de un servicio
     */
    private ArrayList<Videos> videos;

    public ArrayList<Videos> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Videos> videos) {
        this.videos = videos;
    }

    public void addVideo(Videos video){
        if(null != video){
            //En caso de que el arreglo de video sea nulo
            if (videos == null){
                videos = new ArrayList<>();
            }

            videos.add(video);
        }
    }
}
