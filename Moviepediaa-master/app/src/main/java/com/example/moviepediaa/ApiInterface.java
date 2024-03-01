package com.example.moviepediaa;

import com.example.moviepediaa.Models.CastInfo;
import com.example.moviepediaa.Models.MovieDetailsAndVideos;
import com.example.moviepediaa.Models.SearchApiResponse;
import com.example.moviepediaa.Models.Videos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


// For POST request


// for GET request

    @GET("search/movie") // specify the sub url for our base url
    Call<SearchApiResponse> searchMovie(@Query("api_key") String api_key,@Query("query") String query);


    @GET("movie/{movie_id}/credits")
    Call<CastInfo> getCast(@Path("movie_id") Integer id,@Query("api_key") String api_key);

    @GET("movie/popular")
    Call<SearchApiResponse> getPopular(@Query("api_key") String api_key);

    @GET("movie/{movie_id}")
    Call<Videos> getVideos(@Path("movie_id") int id, @Query("api_key") String api_key, @Query("append_to_response") String videos);

    @GET("movie/upcoming")
    Call<SearchApiResponse>  NowPlaying(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<SearchApiResponse>  toprated(@Query("api_key") String api_key);

    @GET("movie/{movie_id}/similar")
    Call<SearchApiResponse> getSimilar(@Path("movie_id") Integer movie_id,@Query("api_key") String api_key);

// SearchApiResponse is a POJO class which receives the response of this API

}

