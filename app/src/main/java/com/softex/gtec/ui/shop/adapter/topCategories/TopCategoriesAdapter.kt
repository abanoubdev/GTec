package com.softex.gtec.ui.shop.adapter.topCategories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softex.gtec.R
import com.softex.gtec.model.topCategories.TopCategoriesResponseItem
import com.softex.gtec.util.ImageUrlDriller

class TopCategoriesAdapter(
    val items: List<TopCategoriesResponseItem>,
    private val listener: TopCategoriesItemClickListener
) :
    RecyclerView.Adapter<TopCategoriesAdapter.TopCategoriesViewHolder>(),
        (TopCategoriesResponseItem) -> Unit {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TopCategoriesViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.top_categories_row_item, viewGroup, false)

        return TopCategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopCategoriesViewHolder, position: Int) {
        holder.bind(items[position], this)
    }

    override fun getItemCount(): Int = items.size

    class TopCategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val topCategoriesIcon: ImageView = itemView.findViewById(R.id.topCategoriesIcon)

        fun bind(item: TopCategoriesResponseItem, listener: (TopCategoriesResponseItem) -> Unit) =
            with(itemView) {
                setOnClickListener { listener(item) }
                val url =
                    ImageUrlDriller.formatImageURL(item.ListFeaturedImages[0].FileID.toString())
                Glide
                    .with(itemView.context)
                    .load(url)
                    .centerCrop()
                    .into(topCategoriesIcon)
            }
    }

    interface TopCategoriesItemClickListener {
        fun onTopCategoryItemCLicked(item: TopCategoriesResponseItem)
    }

    override fun invoke(item: TopCategoriesResponseItem) {
        listener.onTopCategoryItemCLicked(item)
    }
}