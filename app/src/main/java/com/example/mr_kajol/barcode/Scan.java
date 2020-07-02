package com.example.mr_kajol.barcode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Scan {

    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("sku")
    @Expose
    private String sku;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }


    @Override
    public String toString() {
        return "Post{" +
                "longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", sku=" + sku +
                '}';
    }
}
