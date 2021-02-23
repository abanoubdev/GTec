package com.softex.gtec.model.menuItems

data class NavigationMenuResponseItem(
    val ClassificationID: Int,
    val ClassificationName: String,
    val LstCategories: List<Category>,
    val LstChildClassification: List<Any>
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NavigationMenuResponseItem

        if (ClassificationID != other.ClassificationID) return false
        if (ClassificationName != other.ClassificationName) return false
        if (LstCategories != other.LstCategories) return false
        if (LstChildClassification != other.LstChildClassification) return false

        return true
    }

    override fun hashCode(): Int {
        var result = ClassificationID
        result = 31 * result + ClassificationName.hashCode()
        result = 31 * result + LstCategories.hashCode()
        result = 31 * result + LstChildClassification.hashCode()
        return result
    }

    override fun toString(): String {
        return "{ClassificationID=$ClassificationID, ClassificationName='$ClassificationName', LstCategories=$LstCategories, LstChildClassification=$LstChildClassification}"
    }
}