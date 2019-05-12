package com.movilmx.core.ui;

import com.movilmx.networkcontroller.models.popular.ResultsItem;

public abstract class GenericFragmentFactory {
    /**
     * Detalle de un vídeo
     * @param resultsItem objeto que se utiliza para dibujar el detalle de un vídeo
     * @return fragmento que despliega el detalle de un vídeo
     */
    public abstract GenericFragment getMovieDetailFragment(ResultsItem resultsItem);
}
