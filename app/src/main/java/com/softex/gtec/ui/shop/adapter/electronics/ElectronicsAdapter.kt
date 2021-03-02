package com.softex.gtec.ui.shop.adapter.electronics

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softex.gtec.R
import com.softex.gtec.model.newArrivals.NewArrivalsResponseItem
import com.softex.gtec.util.ImageUrlDriller

class ElectronicsAdapter(
    val items: List<NewArrivalsResponseItem>,
    private val listener: ElectronicsItemClickListener
) :
    RecyclerView.Adapter<ElectronicsAdapter.ElectronicsViewHolder>(),
        (NewArrivalsResponseItem) -> Unit {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ElectronicsViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.electronics_row_item, viewGroup, false)

        return ElectronicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ElectronicsViewHolder, position: Int) {
        holder.bind(items[position], this)
    }

    override fun getItemCount(): Int = items.size

    class ElectronicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val electronicsIcon: ImageView = itemView.findViewById(R.id.electronicsIcon)
        private val electronicsName: TextView = itemView.findViewById(R.id.electronicsName)
        private val electronicsPriceAfterDiscount: TextView =
            itemView.findViewById(R.id.electronicsPriceAfterDiscount)
        private val electronicsPriceBeforeDiscount: TextView =
            itemView.findViewById(R.id.electronicsPriceBeforeDiscount)

        fun bind(item: NewArrivalsResponseItem, listener: (NewArrivalsResponseItem) -> Unit) =
            with(itemView) {
                setOnClickListener { listener(item) }
                if (item.ListFeaturedImages.isNotEmpty()) {
                    val url =
                        ImageUrlDriller.formatImageURL(item.ListFeaturedImages[0].FileID.toString())
                    Glide
                        .with(itemView.context)
                        .load(url)
                        .centerCrop()
                        .into(electronicsIcon)
                }
                electronicsName.text = item.ItemGroupName
                electronicsPriceBeforeDiscount.text = item.ItemPriceBeforeDiscount.toString()
                electronicsPriceBeforeDiscount.paintFlags =
                    electronicsName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                electronicsPriceAfterDiscount.text = item.ItemPrice.toString()
            }
    }

    interface ElectronicsItemClickListener {
        fun onTopCategoryItemCLicked(item: NewArrivalsResponseItem)
    }

    override fun invoke(item: NewArrivalsResponseItem) {
        listener.onTopCategoryItemCLicked(item)
    }
}