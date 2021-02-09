package com.softex.gtec.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.softex.gtec.BuildConfig
import com.softex.gtec.retrofit.RetrofitService

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.base_url)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit.Builder): RetrofitService {
        return retrofit.build()
            .create(RetrofitService::class.java)
    }
}