package com.softex.gtec.model

data class ForgetPasswordResponse(
    val MailSent: Boolean,
    val Message: String
) {
    override fun toString(): String {
        return "{MailSent=$MailSent, Message='$Message'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ForgetPasswordResponse

        if (MailSent != other.MailSent) return false
        if (Message != other.Message) return false

        return true
    }

    override fun hashCode(): Int {
        var result = MailSent.hashCode()
        result = 31 * result + Message.hashCode()
        return result
    }


}