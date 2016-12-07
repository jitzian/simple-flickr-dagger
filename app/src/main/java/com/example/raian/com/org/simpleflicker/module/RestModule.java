package com.example.raian.com.org.simpleflicker.module;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by raian on 12/6/16.
 */

@Module
public class RestModule {
    String baseURL;

    public RestModule(String baseURL) {
        this.baseURL = baseURL;
    }

    @Provides
    Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseURL)
                .build();
    }

}
