package com.softex.gtec.model

data class ForgetPasswordRequest(
    val DatabaseName: String,
    val Email: String,
    val EncryptedAppURL: String,
    val EncryptedEXAppID: String,
    val SecurityString: String,
    val ServerIP: String
) {
    override fun toString(): String {
        return "{DatabaseName='$DatabaseName', Email='$Email', EncryptedAppURL='$EncryptedAppURL', EncryptedEXAppID='$EncryptedEXAppID', SecurityString='$SecurityString', ServerIP='$ServerIP'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ForgetPasswordRequest

        if (DatabaseName != other.DatabaseName) return false
        if (Email != other.Email) return false
        if (EncryptedAppURL != other.EncryptedAppURL) return false
        if (EncryptedEXAppID != other.EncryptedEXAppID) return false
        if (SecurityString != other.SecurityString) return false
        if (ServerIP != other.ServerIP) return false

        return true
    }

    override fun hashCode(): Int {
        var result = DatabaseName.hashCode()
        result = 31 * result + Email.hashCode()
        result = 31 * result + EncryptedAppURL.hashCode()
        result = 31 * result + EncryptedEXAppID.hashCode()
        result = 31 * result + SecurityString.hashCode()
        result = 31 * result + ServerIP.hashCode()
        return result
    }
}

