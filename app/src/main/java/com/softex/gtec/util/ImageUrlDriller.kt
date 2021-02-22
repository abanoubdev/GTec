package com.softex.gtec.util

import com.softex.gtec.BuildConfig
import com.softex.gtec.extensions.encrypt

object ImageUrlDriller {

    fun formatImageURL(fileId: String): String {
        var url = BuildConfig.base_url + "api/General/GetImagesContentByID" + "?"
        url += "InputX.securityString=" + BuildConfig.security_string + "&"
        url += "InputX.serverIP=" + BuildConfig.server_ip + "&"
        url += "InputX.databaseName=" + BuildConfig.database_name + "&"
        url += "InputX.encryptedEXAppID=" + BuildConfig.encrypted_ex_app_id + "&"
        url += "InputX.encryptedFileID=" + fileId.encrypt()
        return url
    }
}