package com.softex.gtec.model.topCategories

import com.softex.gtec.model.FeaturedImages

data class TopCategoriesResponseItem(
    val CategoryID: Int,
    val CategoryName: String,
    val ListFeaturedImages: List<FeaturedImages>
)