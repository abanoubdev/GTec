package com.softex.gtec.model.newArrivals

data class NewArrivalsResponseItem(
    val DiscountPercentage: Int,
    val ItemGroupID: Int,
    val ItemGroupName: String,
    val ItemID: Int,
    val ItemName: String,
    val ItemPrice: Int,
    val ItemPriceBeforeDiscount: Int,
    val ListFeaturedImages: List<FeaturedImages>,
    val ShortDescription: String
) {
    override fun toString(): String {
        return "{DiscountPercentage=$DiscountPercentage, ItemGroupID=$ItemGroupID, ItemGroupName='$ItemGroupName', ItemID=$ItemID, ItemName='$ItemName', ItemPrice=$ItemPrice, ItemPriceBeforeDiscount=$ItemPriceBeforeDiscount, ListFeaturedImages=$ListFeaturedImages, ShortDescription='$ShortDescription'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NewArrivalsResponseItem

        if (DiscountPercentage != other.DiscountPercentage) return false
        if (ItemGroupID != other.ItemGroupID) return false
        if (ItemGroupName != other.ItemGroupName) return false
        if (ItemID != other.ItemID) return false
        if (ItemName != other.ItemName) return false
        if (ItemPrice != other.ItemPrice) return false
        if (ItemPriceBeforeDiscount != other.ItemPriceBeforeDiscount) return false
        if (ListFeaturedImages != other.ListFeaturedImages) return false
        if (ShortDescription != other.ShortDescription) return false

        return true
    }

    override fun hashCode(): Int {
        var result = DiscountPercentage
        result = 31 * result + ItemGroupID
        result = 31 * result + ItemGroupName.hashCode()
        result = 31 * result + ItemID
        result = 31 * result + ItemName.hashCode()
        result = 31 * result + ItemPrice
        result = 31 * result + ItemPriceBeforeDiscount
        result = 31 * result + ListFeaturedImages.hashCode()
        result = 31 * result + ShortDescription.hashCode()
        return result
    }


}