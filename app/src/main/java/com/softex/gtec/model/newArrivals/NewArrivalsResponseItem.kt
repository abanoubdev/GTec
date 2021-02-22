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
)