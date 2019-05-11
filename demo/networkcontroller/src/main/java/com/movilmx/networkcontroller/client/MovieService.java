package com.movilmx.networkcontroller.client;

import com.movilmx.networkcontroller.models.topRated.response.TopRated;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("movie/top_rated")
    Call<TopRated> getTopRated(@Query("page") String page, @Query("api_key") String apiKey);
}
