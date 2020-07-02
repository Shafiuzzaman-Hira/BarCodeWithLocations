package com.example.mr_kajol.barcode;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface TrackerService {
    @GET("scan")
    Call<List<User>> getAllUsers(@QueryMap Map<String, String> options);

 /*   @GET("users")
    Call<List<User>> postAllUser();*/

}
