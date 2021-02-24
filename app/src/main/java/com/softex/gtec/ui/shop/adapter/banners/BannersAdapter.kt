package com.softex.gtec.ui.shop.adapter.banners

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softex.gtec.R
import com.softex.gtec.model.featuredImages.BannerResponseItem
import com.softex.gtec.util.BannerUrlDriller

class BannersAdapter(
    val items: List<BannerResponseItem>,
    private val listener: BannerItemClickListener
) :
    RecyclerView.Adapter<BannersAdapter.BannerViewHolder>(),
        (BannerResponseItem) -> Unit {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.banner_row_item, viewGroup, false)

        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(items[position], this)
    }

    override fun getItemCount(): Int = items.size

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val topCategoriesIcon: ImageView = itemView.findViewById(R.id.bannerIcon)

        fun bind(item: BannerResponseItem, listener: (BannerResponseItem) -> Unit) =
            with(itemView) {
                setOnClickListener { listener(item) }
                val url =
                    BannerUrlDriller.formatBannerImageURL(item.FileID.toString())
                Glide
                    .with(itemView.context)
                    .load(url)
                    .centerCrop()
                    .into(topCategoriesIcon)
            }
    }

    interface BannerItemClickListener {
        fun onBannerItemClicked(item: BannerResponseItem)
    }

    override fun invoke(p1: BannerResponseItem) {
        listener.onBannerItemClicked(p1)
    }
}