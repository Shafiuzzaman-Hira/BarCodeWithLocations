package com.example.mr_kajol.barcode;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ISenderService {

    @POST("Data")
    Call<Data> sendPost(@Body Data data);
    @FormUrlEncoded


    @POST("Data")
    Call<Data> createPost(
            @Field("Name") String title,
            @Field("body") String text
    );

    @GET("/registration")
    @FormUrlEncoded
    Call<Model> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("/login")
    @FormUrlEncoded
    Call<Model> login(@Field("username") String username, @Field("password") String password);




}
