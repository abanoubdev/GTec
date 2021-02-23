package com.softex.gtec.model.menuItems

data class Category(
    val CategoryID: Int,
    val CategoryName: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (CategoryID != other.CategoryID) return false
        if (CategoryName != other.CategoryName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = CategoryID
        result = 31 * result + CategoryName.hashCode()
        return result
    }

    override fun toString(): String {
        return "{CategoryID=$CategoryID, CategoryName='$CategoryName'}"
    }
}