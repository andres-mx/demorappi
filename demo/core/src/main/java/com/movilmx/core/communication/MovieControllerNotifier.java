package com.movilmx.core.communication;

public interface MovieControllerNotifier {
    public static final String TAG = MovieControllerNotifier.class.getSimpleName();

    /**
     * Cada evento que es completado en un controlador de perfil es notificado con esta interface
     * @param eventType tipo de evento que fue completado
     * @param movieControllerObject  objeto resultado de la operacion en el controlador.
     */
    void movieControllerEvent(MovieControllerEventType eventType, MovieControllerObject movieControllerObject);

    enum MovieControllerEventType{
        POPULAR, //Películas más populares
        TOPRATED, //Películas top
        UPCOMING, //Peliculas que se estrenarán
        WARNING //Error
    }
}
