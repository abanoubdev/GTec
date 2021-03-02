package com.softex.gtec.retrofit

import com.softex.gtec.model.User
import com.softex.gtec.model.featuredImages.BannerResponseItem
import com.softex.gtec.model.menuItems.NavigationMenuResponseItem
import com.softex.gtec.model.newArrivals.NewArrivalsResponseItem
import com.softex.gtec.model.topCategories.TopCategoriesResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("/api/Account/CustomerLogin")
    suspend fun customerLogin(
        @Query("InputX.securityString") securityString: String,
        @Query("InputX.serverIP") serverIP: String,
        @Query("InputX.databaseName") databaseName: String,
        @Query("InputX.encryptedEXAppID") encryptedEXAppID: String,
        @Query("InputX.encryptedAppURL") encryptedAppURL: String,
        @Query("InputX.email") email: String,
        @Query("InputX.encryptedUserPassword") encryptedPassword: String
    ): User?

    @GET("api/Main/GetCategoriesFeaturedImages")
    suspend fun getTopCategories(
        @Query("InputX.securityString") securityString: String,
        @Query("InputX.serverIP") serverIP: String,
        @Query("InputX.databaseName") databaseName: String,
        @Query("InputX.encryptedEXAppID") encryptedEXAppID: String,
        @Query("InputX.encryptedTreeNodeID") encryptedTreeNodeID: String,
    ): List<TopCategoriesResponseItem>?

    @GET("api/Main/GetNewArrivals")
    suspend fun getNewArrivals(
        @Query("InputX.securityString") securityString: String,
        @Query("InputX.serverIP") serverIP: String,
        @Query("InputX.databaseName") databaseName: String,
        @Query("InputX.encryptedEXAppID") encryptedEXAppID: String,
        @Query("InputX.encryptedTreeNodeID") encryptedTreeNodeID: String,
    ): List<NewArrivalsResponseItem>?

    @GET("api/Main/GetBannerImgInfo")
    suspend fun getBanners(
        @Query("InputX.securityString") securityString: String,
        @Query("InputX.serverIP") serverIP: String,
        @Query("InputX.databaseName") databaseName: String,
        @Query("InputX.encryptedEXAppID") encryptedEXAppID: String,
        @Query("InputX.encryptedTreeNodeClassificationID") encryptedTreeNodeClassificationID: String,
    ): List<BannerResponseItem>?

    @GET("api/Shop/GetCategoriesWithClassification")
    suspend fun getCategoriesWithClassification(
        @Query("InputX.securityString") securityString: String,
        @Query("InputX.serverIP") serverIP: String,
        @Query("InputX.databaseName") databaseName: String,
        @Query("InputX.encryptedEXAppID") encryptedEXAppID: String,
        @Query("InputX.encryptedClassificationID") encryptedClassificationID: String,
    ): List<NavigationMenuResponseItem>?
}