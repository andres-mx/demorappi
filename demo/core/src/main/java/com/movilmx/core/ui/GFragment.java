package com.movilmx.core.ui;

import android.view.View;

import com.movilmx.core.communication.MovieControllerNotifier;
import com.movilmx.core.communication.MovieControllerObject;
import com.movilmx.core.controllers.MovieControllers;

public class GFragment extends GenericFragment implements MovieControllerNotifier {
    public static final String TAG = GFragment.class.getSimpleName();

    @Override
    public void setRootView(View rootView) {
        super.setRootView(rootView);
    }

    /**
     * Método llamado al realizar un evento dentro del controlador.
     * @param eventType            tipo de evento ocurrido
     * @param movieControllerObject objeto de salida, dentro contiene todos los parametros y variables
     */
    @Override
    public void movieControllerEvent(MovieControllerEventType eventType,
                                     MovieControllerObject movieControllerObject) {
        if (eventType == MovieControllerEventType.WARNING){
            manageException(movieControllerObject.getException());
        }
    }

    @Override
    public MovieControllers getMovieController() {
        return MovieControllers.getInstance();
    }
}
