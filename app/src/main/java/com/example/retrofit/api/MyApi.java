package com.example.retrofit.api;

import com.example.retrofit.model.Followers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApi {
    @GET("/users/{user}/followers")
    Call<List<Followers>> getFollowers(@Path("user") String user);

}
