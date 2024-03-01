package com.example.moviepediaa;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static retrofit2.Retrofit.*;

public class Api {
     public static Retrofit retrofit;

    public static Retrofit getRetrofitinstance() {


            if(retrofit==null) {
                retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()) //Setting the Root URL
                        .build(); //Finally building the adapter
            }
        //Creating object for our interface

        return retrofit;
    }

}