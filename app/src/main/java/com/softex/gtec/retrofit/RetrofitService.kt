package com.softex.gtec.retrofit

import com.softex.gtec.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("api/Account/MobileLogin")
    suspend fun login(
        @Query("InputX.securityString") securityString: String,
        @Query("InputX.serverIP") serverIP: String,
        @Query("InputX.databaseName") databaseName: String,
        @Query("InputX.encryptedEXAppID") encryptedEXAppID: String,
        @Query("InputX.encryptedPhone") encryptedPhone: String,
        @Query("InputX.encryptedPassword") encryptedPassword: String
    ): User
}