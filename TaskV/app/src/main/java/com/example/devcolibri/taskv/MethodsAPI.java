package com.example.devcolibri.taskv;


import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface MethodsAPI {
    @GET("/users/{user}/repos")
    void listUser(@Path("user") String user, Callback<List<User>> callback);
}
