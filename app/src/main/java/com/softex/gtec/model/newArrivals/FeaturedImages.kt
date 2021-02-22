package com.softex.gtec.model.newArrivals

data class FeaturedImages(
    val FileGUID: String,
    val FileID: Int
) {
    override fun toString(): String {
        return "{FileGUID='$FileGUID', FileID=$FileID}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FeaturedImages

        if (FileGUID != other.FileGUID) return false
        if (FileID != other.FileID) return false

        return true
    }

    override fun hashCode(): Int {
        var result = FileGUID.hashCode()
        result = 31 * result + FileID
        return result
    }
}