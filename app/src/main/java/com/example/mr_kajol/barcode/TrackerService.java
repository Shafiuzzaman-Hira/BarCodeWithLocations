package com.example.mr_kajol.barcode;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TrackerService {
    @GET("users")
    Call<List<User>> getAllUsers();

 /*   @GET("users")
    Call<List<User>> postAllUser();*/

}
