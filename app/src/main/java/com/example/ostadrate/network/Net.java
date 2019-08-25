package com.example.ostadrate.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class Net {

    private static Retrofit retrofitInstance = null;

    public static Retrofit retrofit() {
        if (retrofitInstance == null) {
            retrofitInstance = new Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl("")
                    .client(getClient())
                    .build();
        }
        return retrofitInstance;
    }

    public static Retrofit retrofit(String baseUrl) {
        if (retrofitInstance == null) {
            retrofitInstance = new Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(baseUrl)
                    .client(getClient())
                    .build();
        }
        return retrofitInstance;
    }

    private static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }
}
