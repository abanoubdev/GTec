package com.softex.gtec.di

import com.softex.gtec.repository.MainRepository
import com.softex.gtec.repository.RepositorySource
import com.softex.gtec.retrofit.RetrofitService
import com.softex.gtec.room.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@InstallIn(ViewModelComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        userDao: UserDao,
        retrofit: RetrofitService,
    ): RepositorySource {
        return MainRepository(
            userDao = userDao,
            retrofitService = retrofit
        )
    }
}