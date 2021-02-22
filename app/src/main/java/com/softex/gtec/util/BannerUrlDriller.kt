package com.softex.gtec.util

import com.softex.gtec.BuildConfig

object BannerUrlDriller {

    fun formatBannerImageURL(fileId: String): String {
        var url = BuildConfig.base_url + "api/Main/GetBannerFileContent" + "?"
        url += "InputX.securityString=" + BuildConfig.security_string + "&"
        url += "InputX.serverIP=" + BuildConfig.server_ip + "&"
        url += "InputX.databaseName=" + BuildConfig.database_name + "&"
        url += "InputX.encryptedEXAppID=" + BuildConfig.encrypted_ex_app_id + "&"
        url += "InputX.encryptedFileID=" + SaltEncryption.encrypt(fileId)
        return url
    }
}