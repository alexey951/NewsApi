package com.example.newsapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/news")
    Call<List<newsModel>> getNews();
}
