package com.movilmx.demorappi;

import android.app.Application;

import com.movilmx.core.controllers.MovieControllers;

public class Movies extends Application {
    public static final String TAG = Movies.class.getSimpleName();

    private static MovieControllers movieControllers;

    @Override
    public void onCreate() {
        super.onCreate();

        try{
            movieControllers = MovieControllers.newInstance(getApplicationContext());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
