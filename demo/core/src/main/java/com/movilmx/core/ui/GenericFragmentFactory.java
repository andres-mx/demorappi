package com.movilmx.core.ui;

import com.movilmx.core.videos.Videos;

public abstract class GenericFragmentFactory {
    /**
     * Detalle de un vídeo
     * @param video objeto que se utiliza para dibujar el detalle de un vídeo
     * @return fragmento que despliega el detalle de un vídeo
     */
    public abstract GenericFragment getMovieDetailFragment(String videoId, Videos video);
}
