package com.org.tmdb.data.remote

import com.org.tmdb.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObjectCreator {

    fun create(): ApiCalls {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .apply {
                                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                else HttpLoggingInterceptor.Level.NONE
                            }
                    )
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiCalls::class.java)
    }
}