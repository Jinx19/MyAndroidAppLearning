package com.example.sony.retrofitxml;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.simpleframework.xml.Path;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by sony on 2017/4/6.
 */

public class Controller implements Callback<RSSFeed> {
    static final String BASE_URL = "http://10.0.2.2";
    private Context context;
    public void start(Context applicationContext){
        context = applicationContext;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(SimpleXmlConverterFactory.create()).build();
        VogellaAPI vogellaAPI = retrofit.create(VogellaAPI.class);
        Call<RSSFeed> call = vogellaAPI.loadRSSReed();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {
        if (response.isSuccessful()) {
            Toast.makeText(context,"Get XML SUCCESS!",Toast.LENGTH_SHORT).show();
            RSSFeed rss = response.body();
            System.out.println("Channel title: " + rss.getChannelTitle());
            rss.getArticleList().forEach(
                    article -> Log.i("TanJin","Title: " + article.getTitle() + " Link: " + article.getLink()));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<RSSFeed> call, Throwable t) {
        t.printStackTrace();
    }
}
