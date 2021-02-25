package com.softex.gtec.ui.homepage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hendraanggrian.recyclerview.widget.ExpandableItem
import com.hendraanggrian.recyclerview.widget.ExpandableRecyclerView
import com.softex.gtec.R
import com.softex.gtec.model.menuItems.NavigationMenuResponseItem

class MenuAdapter(
    layout: LinearLayoutManager,
    val items: List<NavigationMenuResponseItem>,
    private val listener: OnCategoryClickListener,
) :
    ExpandableRecyclerView.Adapter<MenuAdapter.MenuViewHolder>(layout) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.menu_row_item, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val expandableItem: ExpandableItem = itemView.findViewById(R.id.row)

        fun bind(item: NavigationMenuResponseItem, listener: OnCategoryClickListener) {
            expandableItem.headerLayout.findViewById<TextView>(R.id.tvCategoryName).text =
                item.ClassificationName
            if (!item.LstCategories.isNullOrEmpty()) {
                val expandedRecyclerView =
                    expandableItem.contentLayout.findViewById<RecyclerView>(R.id.recyclerExpandedItems)
                expandedRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
                expandedRecyclerView.setHasFixedSize(true)
                expandedRecyclerView.adapter =
                    ExpandedMenuAdapter(item.LstCategories, object : OnCategoryClickListener {
                        override fun onCategoryClicked(categoryId: String) {
                            listener.onCategoryClicked(categoryId)
                        }
                    })
            } else {
                expandableItem.headerLayout.setOnClickListener {
                    listener.onCategoryClicked(item.ClassificationID.toString())
                }
            }
        }
    }
}