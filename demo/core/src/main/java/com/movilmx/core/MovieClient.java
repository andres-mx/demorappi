package com.movilmx.core;

import android.content.Context;

import com.movilmx.networkcontroller.client.NetworkController;

import java.io.IOException;

public class MovieClient extends NetworkController {
    private static final String TAG = MovieClient.class.getSimpleName();
    private static MovieClient movieClient;

    public static MovieClient getInstance(){
        if (null == movieClient){
            throw new NullPointerException("You should be call first MovieClient#newInstance method");
        }

        return movieClient;
    }

    public static MovieClient newInstance(Context context, String baseUrl) throws IOException{
        if (null == movieClient){
            movieClient = new MovieClient(context, baseUrl);
        }

        return movieClient;
    }

    private MovieClient(Context context, String baseUrl) throws IOException{
        super(context, baseUrl);
    }
}