package com.wooeun18.carrotmaketclone;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface RetrofitService {

    @Multipart
    @POST("/Bluemaket/aaa.php")
    Call<String> postDataToServer(@PartMap Map<String, String> dataPart, @Part MultipartBody.Part filePart);

    @GET("/Bluemaket/loadDB.php")
    Call<ArrayList<Item02>> loadDataFromService();

    @PUT("/Retrofit/fileName")
    Call<Item02> updateData(@Path("fileName") String fileName, @Body Item02 item);

}
