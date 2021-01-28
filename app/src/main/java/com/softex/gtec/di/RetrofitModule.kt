package com.softex.gtec.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.softex.gtec.BuildConfig
import com.softex.gtec.retrofit.RetrofitService

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.base_url)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit.Builder): RetrofitService {
        return retrofit.build()
            .create(RetrofitService::class.java)
    }
}