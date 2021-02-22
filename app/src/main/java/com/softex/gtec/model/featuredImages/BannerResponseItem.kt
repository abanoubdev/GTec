package com.softex.gtec.model.featuredImages

data class BannerResponseItem(
    val AltText: String,
    val FileGUID: String,
    val FileID: Int,
    val FileName: String,
    val HTML_RawText: String,
    val HTML_Text: String,
    val HTML_URL: String
){
    override fun toString(): String {
        return "{AltText='$AltText', FileGUID='$FileGUID', FileID=$FileID, FileName='$FileName', HTML_RawText='$HTML_RawText', HTML_Text='$HTML_Text', HTML_URL='$HTML_URL'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BannerResponseItem

        if (AltText != other.AltText) return false
        if (FileGUID != other.FileGUID) return false
        if (FileID != other.FileID) return false
        if (FileName != other.FileName) return false
        if (HTML_RawText != other.HTML_RawText) return false
        if (HTML_Text != other.HTML_Text) return false
        if (HTML_URL != other.HTML_URL) return false

        return true
    }

    override fun hashCode(): Int {
        var result = AltText.hashCode()
        result = 31 * result + FileGUID.hashCode()
        result = 31 * result + FileID
        result = 31 * result + FileName.hashCode()
        result = 31 * result + HTML_RawText.hashCode()
        result = 31 * result + HTML_Text.hashCode()
        result = 31 * result + HTML_URL.hashCode()
        return result
    }
}