package com.softex.gtec.util

import android.util.Base64

object SaltEncryption {

    private const val firstSalt: String = "*&*$(#-j90"
    private const val secondSalt: String = "(\$34#45(*)"

    fun encrypt(txt: String): String {
        return Base64.encode(
            "$firstSalt$txt$secondSalt".toByteArray(Charsets.US_ASCII),
            Base64.DEFAULT
        )
            .toString(charset("UTF-8")).replace("\n", "")
    }
}