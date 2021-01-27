package com.softex.gtec.di

import android.content.Context
import androidx.room.Room
import com.softex.gtec.room.UserDao
import com.softex.gtec.room.GtecDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideGTecDb(@ApplicationContext context: Context): GtecDatabase {
        return Room.databaseBuilder(
            context,
            GtecDatabase::class.java,
            GtecDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDAO(gtecDatabase: GtecDatabase): UserDao {
        return gtecDatabase.userDao()
    }
}