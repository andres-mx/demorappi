package com.movilmx.core.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.movilmx.core.AbstractMovieController;
import com.movilmx.core.BuildConfig;
import com.movilmx.core.communication.MovieControllerNotifier;
import com.movilmx.core.communication.MovieControllerObject;

import java.util.Objects;

public abstract class GenericFragment extends Fragment implements MovieControllerNotifier {
    public static String TAG = GenericFragment.class.getSimpleName();

    private View rootView;//Layout general del fragment

    public View getRootView() {
        return rootView;
    }

    /**
     * Despues de la llamada del metodo @onCreateView vez inicializado el fragmento es necesario
     * llamar a este metodo para colocar el layout de fragmento como rootView
     * @param rootView vista contenedora del fragmento
     */
    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    protected void manageException(Exception e){
        try{
            if (BuildConfig.DEBUG){
                e.printStackTrace();
                GenericSnackBar genericSnackBar = new GenericSnackBar();
                genericSnackBar.view(
                        Objects.requireNonNull(getActivity()).getCurrentFocus(),
                        -1,
                        getActivity())
                        .text(e.getMessage(), e.getMessage())
                        .duration(GenericSnackBar.SnackBarDuration.SHORT)
                        .show(true);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void movieControllerEvent(MovieControllerEventType eventType,
                                     MovieControllerObject movieControllerObject) {
        if (eventType == MovieControllerEventType.WARNING){
            manageException(movieControllerObject.getException());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    //free up any drawables..views
    private void unbindDrawables(View view) {
        if(null != view){
            if (view.getBackground() != null) {
                view.getBackground().setCallback(null);
            }
            if (view instanceof ViewGroup) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    unbindDrawables(((ViewGroup) view).getChildAt(i));
                }
                if (!(view instanceof AdapterView<?>))
                    ((ViewGroup) view).removeAllViews();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindDrawables(rootView);
    }

    public GenericActivity getWGActivity(){
        return (GenericActivity) getActivity();
    }

    public abstract AbstractMovieController getMovieController();
}
