package com.softex.gtec.model.featuredImages

data class BannerResponseItem(
    val AltText: String,
    val FileGUID: String,
    val FileID: Int,
    val FileName: String,
    val HTML_RawText: String,
    val HTML_Text: String,
    val HTML_URL: String
)