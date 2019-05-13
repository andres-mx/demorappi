package com.movilmx.core.conversor;

import com.movilmx.core.videos.Container;
import com.movilmx.core.videos.Videos;
import com.movilmx.networkcontroller.models.popular.Popular;
import com.movilmx.networkcontroller.models.popular.ResultsItem;
import com.movilmx.networkcontroller.models.topRated.response.TopRated;
import com.movilmx.networkcontroller.models.upComing.UpComing;

public class Middleware {
    public static Container cleanResponse(Object data) throws Exception{
        if (data == null){
            throw new NullPointerException("El objeto a transformar no debe de ser null");
        }

        Container container = new Container();

        if (data instanceof Popular){
            Popular popular = (Popular) data;
            container.setPageNumber(popular.getPage()+1);
            if (null != popular.getResults() && popular.getResults().size()>0){
                for (ResultsItem r: popular.getResults()) {
                    Videos videos = cleanVideos(r);

                    if (null != videos){
                        container.addVideo(videos);
                    }
                }
            }
        }else if (data instanceof TopRated){
            TopRated topRated = (TopRated) data;
            container.setPageNumber(topRated.getPage()+1);
            if (null != topRated.getResults() && topRated.getResults().size()>0){
                for (ResultsItem r: topRated.getResults()) {
                    Videos videos = cleanVideos(r);

                    if (null != videos){
                        container.addVideo(videos);
                    }
                }
            }
        }else if (data instanceof UpComing){
            UpComing upComing = (UpComing) data;
            container.setPageNumber(upComing.getPage()+1);
            if (null != upComing.getResults() && upComing.getResults().size()>0){
                for (ResultsItem r: upComing.getResults()) {
                    Videos videos = cleanVideos(r);

                    if (null != videos){
                        container.addVideo(videos);
                    }
                }
            }
        }

        return container;
    }

    /**
     * Convierte los diferentes tipos de resultados de videos {@link Videos}
     * @param video: objeto a convertir
     * @return: un objeto de tip {@link Videos}
     */
    public static Videos cleanVideos(Object video){
        Videos videos = null;
        try{
            if (null == video){
                throw new Exception("El video a convertir no debe ser null");
            }

            if (video instanceof ResultsItem){
                ResultsItem resultsItem = (ResultsItem) video;
                videos = new Videos();
                videos.setTitle(resultsItem.getTitle());
                videos.setDescription(resultsItem.getOverview());
                videos.setImage(resultsItem.getPosterPath());
                videos.setId(resultsItem.getId());
                videos.setDate(resultsItem.getReleaseDate());
                videos.setPopularity(resultsItem.getPopularity());
                videos.setVoteAverage(resultsItem.getVoteAverage());
                videos.setVoteCount(resultsItem.getVoteCount());

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return videos;
    }
}
