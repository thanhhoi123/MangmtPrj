package com.example.quanlybanhang.data.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ListApi {

    @GET("/api/v1/getSP")
    Single<List<Product>> getProduct();

    @POST("/api/login")
    Call<User> createPost(@Body User user);

    @POST("api/v2/BuySP")
    Call<ApiOrder> createOrder(@Body ApiOrder apiOrder);

    @POST("api/v2/getDH")
    Call<List<ApiHistory>> createHistory(@Body ApiHistory apiHistory);

    @POST("/api/v3/detail")
    Call<ApiDetail> createDetail(@Body ApiDetail apiDetail);
}
