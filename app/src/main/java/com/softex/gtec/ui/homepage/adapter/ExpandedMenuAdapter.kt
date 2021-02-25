package com.softex.gtec.ui.homepage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.softex.gtec.R
import com.softex.gtec.model.menuItems.Category

class ExpandedMenuAdapter(
    private val lstCategories: List<Category>,
    private val listener: OnCategoryClickListener
) :
    RecyclerView.Adapter<ExpandedMenuAdapter.ExpandedViewHolder>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ExpandedViewHolder {
        return ExpandedViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.expanded_menu_row_item, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ExpandedViewHolder, position: Int) {
        holder.bind(lstCategories[position], listener)
    }

    override fun getItemCount(): Int = lstCategories.size

    class ExpandedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvExpandedCategoryName: TextView =
            itemView.findViewById(R.id.tvExpandedCategoryName)

        fun bind(category: Category, listener: OnCategoryClickListener) {
            tvExpandedCategoryName.text = category.CategoryName
            itemView.setOnClickListener {
                listener.onCategoryClicked(category.CategoryID.toString())
            }
        }
    }
}