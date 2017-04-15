package com.example.sony.retrofitxml;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sony on 2017/4/6.
 */

public interface VogellaAPI {
    @GET("article.rss")
    Call<RSSFeed> loadRSSReed();
}
