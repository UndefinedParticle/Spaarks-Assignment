package com.example.spaarksassignment;

import com.example.spaarksassignment.Models.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApiService {
    // Define the endpoint for fetching the list of users
    @GET("users")
    Call<ArrayList<Users>> getUsers();
}
