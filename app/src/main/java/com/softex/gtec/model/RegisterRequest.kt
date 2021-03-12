package com.softex.gtec.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("CityID")
    val CityID: Int,
    @SerializedName("CountryID")
    val CountryID: Int,
    @SerializedName("CustomerFullName")
    val CustomerFullName: String,
    @SerializedName("DatabaseName")
    val DatabaseName: String,
    @SerializedName("Email")
    val Email: String,
    @SerializedName("EncryptedAppURL")
    val EncryptedAppURL: String,
    @SerializedName("EncryptedEXAppID")
    val EncryptedEXAppID: String,
    @SerializedName("EncryptedPassword")
    val EncryptedPassword: String,
    @SerializedName("IPAddress")
    val IPAddress: String,
    @SerializedName("SecurityString")
    val SecurityString: String,
    @SerializedName("ServerIP")
    val ServerIP: String,
    @SerializedName("UserRegisterationDetails")
    val UserRegisterationDetails: String
)