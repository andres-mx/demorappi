package com.movilmx.core;

import com.movilmx.core.communication.MovieControllerNotifier;
import com.movilmx.networkcontroller.client.NetworkController;

/**
 * Clase abstracta del controlador de movies
 * La intención es tener un objeto génerico para los métodos de {@link AbstractMovieController}
 */
public abstract class AbstractMovieController extends AbstractController {

    public AbstractMovieController(NetworkController client) {
        super(client);
    }

    public abstract void requestMovies(String numberPage, final MovieControllerNotifier movieControllerNotifier) throws Exception;

    public abstract void requestUpComing(String numberPage, final MovieControllerNotifier movieControllerNotifier) throws Exception;

    public abstract void requestPopular(String numberPage, final  MovieControllerNotifier movieControllerNotifier) throws Exception;
}
