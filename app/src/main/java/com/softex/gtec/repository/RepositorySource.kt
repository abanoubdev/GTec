package com.softex.gtec.repository

import com.softex.gtec.model.User
import com.softex.gtec.model.featuredImages.BannerResponse
import com.softex.gtec.model.menuItems.NavigationMenuResponse
import com.softex.gtec.model.newArrivals.NewArrivalsResponse
import com.softex.gtec.model.topCategories.TopCategoriesResponse
import com.softex.gtec.util.DataState
import kotlinx.coroutines.flow.Flow

interface RepositorySource {

    suspend fun login(username: String, password: String): Flow<DataState<User?>>

    suspend fun getCachedUser(): Flow<DataState<User?>>

    suspend fun getTopCategories(): Flow<DataState<TopCategoriesResponse?>>

    suspend fun getElectronics(): Flow<DataState<NewArrivalsResponse?>>

    suspend fun getHomeAppliance(): Flow<DataState<NewArrivalsResponse?>>

    suspend fun getBanners(): Flow<DataState<BannerResponse?>>

    suspend fun getMenuItems(): Flow<DataState<NavigationMenuResponse?>>
}