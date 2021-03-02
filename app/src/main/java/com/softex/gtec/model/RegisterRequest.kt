package com.softex.gtec.model

data class RegisterRequest(
    val CityID: Int,
    val CountryID: Int,
    val CustomerFullName: String,
    val DatabaseName: String,
    val Email: String,
    val EncryptedAppURL: String,
    val EncryptedEXAppID: String,
    val EncryptedPassword: String,
    val IPAddress: String,
    val SecurityString: String,
    val ServerIP: String,
    val UserRegisterationDetails: String
) {
    override fun toString(): String {
        return "{CityID=$CityID, CountryID=$CountryID, CustomerFullName='$CustomerFullName', DatabaseName='$DatabaseName', Email='$Email', EncryptedAppURL='$EncryptedAppURL', EncryptedEXAppID='$EncryptedEXAppID', EncryptedPassword='$EncryptedPassword', IPAddress='$IPAddress', SecurityString='$SecurityString', ServerIP='$ServerIP', UserRegisterationDetails='$UserRegisterationDetails'}"
    }
}