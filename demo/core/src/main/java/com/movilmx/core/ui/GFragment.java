package com.movilmx.core.ui;

import android.view.View;

import androidx.annotation.CallSuper;

import com.movilmx.core.communication.MovieControllerNotifier;
import com.movilmx.core.communication.MovieControllerObject;
import com.movilmx.core.controllers.MovieControllers;
import com.movilmx.core.videos.Videos;

public class GFragment extends GenericFragment implements MovieControllerNotifier, UIEvent {
    public static final String TAG = GFragment.class.getSimpleName();

    @Override
    public void setRootView(View rootView) {
        super.setRootView(rootView);
    }

    public void detailVideo(int id, Videos videos){
        getGActivity().addFragment(
                getGActivity()
                        .getFragmentsFactory()
                        .getMovieDetailFragment(id, videos));
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

    @CallSuper
    @Override
    public void event(UIEventType eventType, UIObject uiObject) {
        if (eventType == UIEventType.WARNING){
            manageException(uiObject.getException());
        }
    }
}
