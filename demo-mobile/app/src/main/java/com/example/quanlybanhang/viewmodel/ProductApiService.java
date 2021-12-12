package com.example.quanlybanhang.viewmodel;

import com.example.quanlybanhang.data.model.ListApi;
import com.example.quanlybanhang.data.model.Product;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductApiService {
    private static final String BASE_URL = "https://quanlybanhangapi.herokuapp.com";
    private ListApi api;
    public ProductApiService(){
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ListApi.class);
    }

    public Single<List<Product>> getProduct() {return api.getProduct();}
}
