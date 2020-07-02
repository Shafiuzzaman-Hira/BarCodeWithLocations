package com.example.mr_kajol.barcode;

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://192.168.43.171/";

    public static IScannerService getScannerService() {

        return RetrofitClient.getClient(BASE_URL).create(IScannerService.class);
    }
}
