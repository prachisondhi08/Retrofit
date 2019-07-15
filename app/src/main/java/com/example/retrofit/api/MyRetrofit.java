package com.example.retrofit.api;

import com.example.retrofit.model.Followers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MyRetrofit {
    private static Retrofit retrofit = null;
    private static Retrofit init(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static MyApi api = init().create(MyApi.class);

}
