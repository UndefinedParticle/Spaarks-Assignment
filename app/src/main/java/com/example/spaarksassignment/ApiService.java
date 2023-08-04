package com.example.spaarksassignment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiService {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static Retrofit retrofit = null;

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // Add the trailing slash at the end
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static UserApiService getUserApiService() {
        return getRetrofitInstance().create(UserApiService.class);
    }
}

