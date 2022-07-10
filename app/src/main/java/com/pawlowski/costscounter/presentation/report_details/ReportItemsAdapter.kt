package com.pawlowski.costscounter.presentation.report_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pawlowski.costscounter.R
import com.pawlowski.costscounter.data.entities.CategoryWithItems
import com.pawlowski.costscounter.data.entities.CostItemEntity

class ReportItemsAdapter: RecyclerView.Adapter<ReportItemsAdapter.ReportItemHolder>() {

    private var itemsAndCategories: List<Any> = listOf()

    companion object {
        const val COST_ITEM_VIEW = 1
        const val CATEGORY_ITEM_VIEW = 2
    }


    fun resetItemsAndCategories(items: List<CostItemEntity>, categories: List<CategoryWithItems>)
    {
        val newList = mutableListOf<Any>()
        newList.addAll(categories)
        newList.addAll(items)
        itemsAndCategories = newList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportItemHolder {
        return if(viewType == CATEGORY_ITEM_VIEW) {
            ReportItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false), viewType)
        }
        else
        {
            ReportItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.cost_item, parent, false), viewType)
        }

    }

    override fun onBindViewHolder(holder: ReportItemHolder, position: Int) {
        val currentItemOrCategory = itemsAndCategories[position]
        if(currentItemOrCategory is CostItemEntity)
        {
            val currentItem = currentItemOrCategory as CostItemEntity
            holder.tittleTextView.text = currentItem.name
            holder.costTextView.text = "${currentItem.cost}"
        }
        else if(currentItemOrCategory is CategoryWithItems)
        {
            val currentCategory = currentItemOrCategory as CategoryWithItems
            holder.categoryTittleTextView.text = currentCategory.categoryEntity.categoryName
            holder.categoryElementsCountTextView.text = "${currentCategory.items.size}"
        }
    }

    override fun getItemCount(): Int {
        return itemsAndCategories.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(itemsAndCategories[position] is CostItemEntity)
            COST_ITEM_VIEW
        else
            CATEGORY_ITEM_VIEW
    }

    class ReportItemHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView)
    {
        //If it's cost item
        lateinit var tittleTextView: TextView
        lateinit var costTextView: TextView

        //If it's category item
        lateinit var categoryTittleTextView: TextView
        lateinit var categoryElementsCountTextView: TextView

        init {
            if(viewType == COST_ITEM_VIEW)
            {
                tittleTextView = itemView.findViewById(R.id.tittle_text_cost_item)
                costTextView = itemView.findViewById(R.id.cost_text_cost_item)
            }
            else
            {
                categoryTittleTextView = itemView.findViewById(R.id.tittle_text_category_item)
                categoryElementsCountTextView = itemView.findViewById(R.id.elements_count_text_category_item)
            }

        }
    }
}