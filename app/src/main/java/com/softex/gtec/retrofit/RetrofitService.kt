package com.softex.gtec.retrofit

import com.softex.gtec.model.*
import com.softex.gtec.model.featuredImages.BannerResponseItem
import com.softex.gtec.model.menuItems.NavigationMenuResponseItem
import com.softex.gtec.model.newArrivals.NewArrivalsResponseItem
import com.softex.gtec.model.topCategories.TopCategoriesResponseItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @GET("/api/Account/CustomerLogin")
    suspend fun customerLogin(
        @Query("securityString") securityString: String,
        @Query("serverIP") serverIP: String,
        @Query("databaseName") databaseName: String,
        @Query("encryptedEXAppID") encryptedEXAppID: String,
        @Query("encryptedAppURL") encryptedAppURL: String,
        @Query("email") email: String,
        @Query("encryptedUserPassword") encryptedPassword: String
    ): User?

    @POST("api/Account/RegisterNewCustomer")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Int?

    @POST("api/Account/ForgotPassword")
    suspend fun forgetPassword(
        @Body forgetPasswordRequest: ForgetPasswordRequest
    ): ForgetPasswordResponse?

    @GET("api/Main/GetCategoriesFeaturedImages")
    suspend fun getTopCategories(
        @Query("securityString") securityString: String,
        @Query("serverIP") serverIP: String,
        @Query("databaseName") databaseName: String,
        @Query("encryptedEXAppID") encryptedEXAppID: String,
        @Query("encryptedTreeNodeID") encryptedTreeNodeID: String,
    ): List<TopCategoriesResponseItem>?

    @GET("api/Main/GetNewArrivals")
    suspend fun getNewArrivals(
        @Query("securityString") securityString: String,
        @Query("serverIP") serverIP: String,
        @Query("databaseName") databaseName: String,
        @Query("encryptedEXAppID") encryptedEXAppID: String,
        @Query("encryptedTreeNodeID") encryptedTreeNodeID: String,
    ): List<NewArrivalsResponseItem>?

    @GET("api/Main/GetBannerImgInfo")
    suspend fun getBanners(
        @Query("securityString") securityString: String,
        @Query("serverIP") serverIP: String,
        @Query("databaseName") databaseName: String,
        @Query("encryptedEXAppID") encryptedEXAppID: String,
        @Query("encryptedTreeNodeClassificationID") encryptedTreeNodeClassificationID: String,
    ): List<BannerResponseItem>?

    @GET("api/Shop/GetCategoriesWithClassification")
    suspend fun getCategoriesWithClassification(
        @Query("securityString") securityString: String,
        @Query("serverIP") serverIP: String,
        @Query("databaseName") databaseName: String,
        @Query("encryptedEXAppID") encryptedEXAppID: String,
        @Query("encryptedClassificationID") encryptedClassificationID: String,
    ): List<NavigationMenuResponseItem>?

    @GET("api/General/GetCountries")
    suspend fun getCountries(
        @Query("securityString") securityString: String,
        @Query("serverIP") serverIP: String,
        @Query("databaseName") databaseName: String,
        @Query("encryptedEXAppID") encryptedEXAppID: String
    ): List<Country>?

    @GET("api/General/GetCities")
    suspend fun getCities(
        @Query("securityString") securityString: String,
        @Query("serverIP") serverIP: String,
        @Query("databaseName") databaseName: String,
        @Query("encryptedEXAppID") encryptedEXAppID: String
    ): List<City>?
}