package com.softex.gtec.retrofit

import com.softex.gtec.model.User
import com.softex.gtec.model.featuredImages.BannerResponse
import com.softex.gtec.model.menuItems.NavigationMenuResponse
import com.softex.gtec.model.newArrivals.NewArrivalsResponse
import com.softex.gtec.model.topCategories.TopCategoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("api/Account/MobileLogin")
    suspend fun mobileLogin(
        @Query("InputX.securityString") securityString: String,
        @Query("InputX.serverIP") serverIP: String,
        @Query("InputX.databaseName") databaseName: String,
        @Query("InputX.encryptedEXAppID") encryptedEXAppID: String,
        @Query("InputX.encryptedPhone") encryptedPhone: String,
        @Query("InputX.encryptedPassword") encryptedPassword: String
    ): User?

    @GET("api/Main/GetCategoriesFeaturedImages")
    suspend fun getTopCategories(
        @Query("InputX.securityString") securityString: String,
        @Query("InputX.serverIP") serverIP: String,
        @Query("InputX.databaseName") databaseName: String,
        @Query("InputX.encryptedEXAppID") encryptedEXAppID: String,
        @Query("InputX.encryptedTreeNodeID") encryptedTreeNodeID: String,
    ): TopCategoriesResponse?

    @GET("api/Main/GetNewArrivals")
    suspend fun getNewArrivals(
        @Query("InputX.securityString") securityString: String,
        @Query("InputX.serverIP") serverIP: String,
        @Query("InputX.databaseName") databaseName: String,
        @Query("InputX.encryptedEXAppID") encryptedEXAppID: String,
        @Query("InputX.encryptedTreeNodeID") encryptedTreeNodeID: String,
    ): NewArrivalsResponse?

    @GET("api/Main/GetBannerImgInfo")
    suspend fun getBanners(
        @Query("InputX.securityString") securityString: String,
        @Query("InputX.serverIP") serverIP: String,
        @Query("InputX.databaseName") databaseName: String,
        @Query("InputX.encryptedEXAppID") encryptedEXAppID: String,
        @Query("InputX.encryptedTreeNodeID") encryptedTreeNodeID: String,
    ): BannerResponse?

    @GET("api/Shop/GetCategoriesWithClassification")
    suspend fun getCategoriesWithClassification(
        @Query("InputX.securityString") securityString: String,
        @Query("InputX.serverIP") serverIP: String,
        @Query("InputX.databaseName") databaseName: String,
        @Query("InputX.encryptedEXAppID") encryptedEXAppID: String,
        @Query("InputX.encryptedClassificationID") encryptedClassificationID: String,
    ): NavigationMenuResponse?

}