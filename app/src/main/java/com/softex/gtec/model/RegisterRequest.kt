package com.softex.gtec.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("InputX.CityID")
    val CityID: Int,
    @SerializedName("InputX.CountryID")
    val CountryID: Int,
    @SerializedName("InputX.CustomerFullName")
    val CustomerFullName: String,
    @SerializedName("InputX.DatabaseName")
    val DatabaseName: String,
    @SerializedName("InputX.Email")
    val Email: String,
    @SerializedName("InputX.EncryptedAppURL")
    val EncryptedAppURL: String,
    @SerializedName("InputX.EncryptedEXAppID")
    val EncryptedEXAppID: String,
    @SerializedName("InputX.EncryptedPassword")
    val EncryptedPassword: String,
    @SerializedName("InputX.IPAddress")
    val IPAddress: String,
    @SerializedName("InputX.SecurityString")
    val SecurityString: String,
    @SerializedName("InputX.ServerIP")
    val ServerIP: String,
    @SerializedName("InputX.UserRegisterationDetails")
    val UserRegisterationDetails: String
)