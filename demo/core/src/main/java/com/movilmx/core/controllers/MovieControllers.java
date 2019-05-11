package com.movilmx.core.controllers;

import android.content.Context;
import android.util.Log;

import com.movilmx.core.AbstractMovieController;
import com.movilmx.core.BuildConfig;
import com.movilmx.core.MovieClient;
import com.movilmx.core.builder.MovieControllerObjectBuilder;
import com.movilmx.core.communication.MovieControllerNotifier;
import com.movilmx.core.communication.MovieControllerObject;
import com.movilmx.networkcontroller.client.NetworkController;
import com.movilmx.networkcontroller.models.topRated.response.TopRated;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieControllers extends AbstractMovieController {
    private static String           TAG = MovieControllers.class.getSimpleName();
    private static MovieControllers movieControllers;
    public MovieControllers(NetworkController client) {
        super(client);
    }

    public static MovieControllers getInstance(Context context) throws Exception{
        if (null == movieControllers){
            movieControllers = new MovieControllers(MovieClient.newInstance(
                    context, BuildConfig.THEMOVIE));
        }

        return movieControllers;
    }

    @Override
    public void requestMovies(String numberPage, final MovieControllerNotifier movieControllerNotifier)
            throws Exception {
        Call<TopRated> call = getClient().getMovieService().getTopRated
                ("1", "cf689d1c71b97032eca0391929094623");
        call.enqueue(new Callback<TopRated>() {
            @Override
            public void onResponse(Call<TopRated> call, Response<TopRated> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                MovieControllerObjectBuilder movieControllerObjectBuilder
                        = new MovieControllerObjectBuilder();
                try{
                    if (null != response){
                        TopRated topRated = response.body();

                        movieControllerObjectBuilder.setCode(0)
                                .setMsg("OK")
                                .setData(topRated);

                        movieControllerNotifier.movieControllerEvent(
                                MovieControllerNotifier.MovieControllerEventType.TOPRATED,
                                movieControllerObjectBuilder.build());
                    }else {
                        movieControllerNotifier.movieControllerEvent(
                                MovieControllerNotifier.MovieControllerEventType.WARNING,
                                new MovieControllerObject().setCode(-1).setMsg("La respuesta es nula"));
                    }
                }catch(Exception e){
                    movieControllerNotifier.movieControllerEvent(
                            MovieControllerNotifier.MovieControllerEventType.WARNING,
                            new MovieControllerObject(e));
                }
            }

            @Override
            public void onFailure(Call<TopRated> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                movieControllerNotifier.movieControllerEvent(
                        MovieControllerNotifier.MovieControllerEventType.WARNING,
                        new MovieControllerObject().setCode(-1).setMsg(t.getLocalizedMessage()));
            }
        });
    }
}
