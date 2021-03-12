package com.softex.gtec.util

import android.net.Uri
import com.softex.gtec.BuildConfig
import com.softex.gtec.extensions.encrypt


object ImageUrlDriller {

    fun formatImageURL(fileId: String): String {

        return Uri.parse(BuildConfig.base_url + "api/General/GetImagesContentByID")
            .buildUpon()
            .appendQueryParameter("securityString", BuildConfig.security_string)
            .appendQueryParameter("serverIP", BuildConfig.server_ip)
            .appendQueryParameter("databaseName", BuildConfig.database_name)
            .appendQueryParameter("encryptedEXAppID", BuildConfig.encrypted_ex_app_id)
            .appendQueryParameter("encryptedFileID", fileId.encrypt())
            .appendQueryParameter("mediaMode", 1.toString())
            .build().toString()
    }
}