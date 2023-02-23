package com.org.tmdb.di

import com.org.tmdb.data.remote.ApiCalls
import com.org.tmdb.data.remote.RetrofitObjectCreator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TMDBAppModule {

    @Singleton
    @Provides
    fun provideApiCall(): ApiCalls = RetrofitObjectCreator.create()

}