package com.movilmx.networkcontroller.client;

import com.movilmx.networkcontroller.models.detailMovies.DetailMovie;
import com.movilmx.networkcontroller.models.popular.Popular;
import com.movilmx.networkcontroller.models.topRated.response.TopRated;
import com.movilmx.networkcontroller.models.upComing.UpComing;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {
    @GET("movie/top_rated")
    Call<TopRated> getTopRated(@Query("page") String page, @Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<UpComing> getUpComing(@Query("page") String page, @Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<Popular> getPopular(@Query("page") String page, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<DetailMovie> getDetailMovie(@Path("movie_id") String movieID, @Query("api_key") String apiKey);
}
