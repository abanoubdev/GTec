package com.softex.gtec.util

import android.net.Uri
import com.softex.gtec.BuildConfig
import com.softex.gtec.extensions.encrypt


object ImageUrlDriller {

    fun formatImageURL(fileId: String): String {

        return Uri.parse(BuildConfig.base_url + "api/General/GetImagesContentByID")
            .buildUpon()
            .appendQueryParameter("InputX.securityString", BuildConfig.security_string)
            .appendQueryParameter("InputX.serverIP", BuildConfig.server_ip)
            .appendQueryParameter("InputX.databaseName", BuildConfig.database_name)
            .appendQueryParameter("InputX.encryptedEXAppID", BuildConfig.encrypted_ex_app_id)
            .appendQueryParameter("InputX.encryptedFileID", fileId.encrypt())
            .appendQueryParameter("InputX.mediaMode", 1.toString())
            .build().toString()
    }
}