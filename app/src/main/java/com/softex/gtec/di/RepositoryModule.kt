package com.softex.gtec.di

import com.softex.gtec.repository.MainRepository
import com.softex.gtec.retrofit.BlogRetrofit
import com.softex.gtec.retrofit.NetworkMapper
import com.softex.gtec.room.BlogDao
import com.softex.gtec.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(
            blogDao = blogDao,
            blogRetrofit = retrofit,
            cacheMapper = cacheMapper,
            networkMapper = networkMapper
        )
    }
}