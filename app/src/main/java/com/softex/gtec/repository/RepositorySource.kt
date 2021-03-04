package com.softex.gtec.repository

import com.softex.gtec.model.Country
import com.softex.gtec.model.User
import com.softex.gtec.model.featuredImages.BannerResponseItem
import com.softex.gtec.model.menuItems.NavigationMenuResponseItem
import com.softex.gtec.model.newArrivals.NewArrivalsResponseItem
import com.softex.gtec.model.topCategories.TopCategoriesResponseItem
import com.softex.gtec.util.DataState
import kotlinx.coroutines.flow.Flow

interface RepositorySource {

    suspend fun login(username: String, password: String): Flow<DataState<User?>>

    suspend fun register(
        name: String,
        usernameOrEmail: String,
        password: String
    ): Flow<DataState<Int?>>

    suspend fun getCachedUser(): Flow<DataState<User?>>

    suspend fun getTopCategories(): Flow<DataState<List<TopCategoriesResponseItem>?>>

    suspend fun getElectronics(): Flow<DataState<List<NewArrivalsResponseItem>?>>

    suspend fun getHomeAppliance(): Flow<DataState<List<NewArrivalsResponseItem>?>>

    suspend fun getBanners(): Flow<DataState<List<BannerResponseItem>?>>

    suspend fun getMenuItems(): Flow<DataState<List<NavigationMenuResponseItem>?>>

    suspend fun getCountriesWithCities(): Flow<DataState<List<Country>?>>

    suspend fun reset()
}