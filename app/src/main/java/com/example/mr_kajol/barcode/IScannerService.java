package com.example.mr_kajol.barcode;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IScannerService {
    @POST("/users")
    @FormUrlEncoded
    Call<Scan> sendScannedData(@Field("longitude") String longitude,
                        @Field("latitude") String latitude,
                        @Field("sku") String sku);
}
