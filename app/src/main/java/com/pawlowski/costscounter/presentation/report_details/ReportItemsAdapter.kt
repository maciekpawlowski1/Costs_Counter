package com.pawlowski.costscounter.presentation.report_details

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.pawlowski.costscounter.R
import com.pawlowski.costscounter.base.SelectableAdapter
import com.pawlowski.costscounter.data.entities.CategoryEntity
import com.pawlowski.costscounter.data.entities.CategoryWithItems
import com.pawlowski.costscounter.data.entities.CostItemEntity

class ReportItemsAdapter(private val itemOrCategoryButtonsClickListener: ItemOrCategoryButtonsClickListener): SelectableAdapter<ReportItemsAdapter.ReportItemHolder>() {

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

    fun getSelectedItems(): List<CostItemEntity>
    {
        val items = mutableListOf<CostItemEntity>()
        for(i in positionsSelected)
        {
            if(itemsAndCategories[i] is CostItemEntity)
            {
                items.add(itemsAndCategories[i] as CostItemEntity)
            }
        }
        return items
    }

    fun getSelectedCategories(): List<CategoryWithItems>
    {
        val categories = mutableListOf<CategoryWithItems>()
        for(i in positionsSelected)
        {
            if(itemsAndCategories[i] is CategoryWithItems)
            {
                categories.add(itemsAndCategories[i] as CategoryWithItems)
            }
        }
        return categories
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
            holder.costTextView.text = "${"%.2f".format(currentItem.cost)}"
            holder.elementsCountTextView.text = "(${currentItem.amount}x)"

            holder.cardView.setOnClickListener {
                if(isSomethingSelected())
                {
                    selectOrUnselectPosition(position)
                }
                else
                {
                    itemOrCategoryButtonsClickListener.onItemCardClick(currentItem)
                }
            }

            holder.cardView.setOnLongClickListener {
                selectOrUnselectPosition(position)
                return@setOnLongClickListener true
            }

            if(isPositionSelected(position))
            {
                holder.cardView.setBackgroundColor(Color.YELLOW)
            }
            else
            {
                holder.cardView.setBackgroundColor(Color.WHITE)
            }

        }
        else if(currentItemOrCategory is CategoryWithItems)
        {
            val currentCategory = currentItemOrCategory as CategoryWithItems
            holder.categoryTittleTextView.text = currentCategory.categoryEntity.categoryName

            val sum = currentCategory.items.sumOf { it.cost*it.amount }
            holder.summaryCostText.text = "${"%.2f".format(sum)}"

            holder.categoryCardView.setOnClickListener {
                if(isSomethingSelected())
                {
                    selectOrUnselectPosition(position)
                }
                else
                {
                    itemOrCategoryButtonsClickListener.onCategoryCardClick(currentCategory.categoryEntity)
                }
            }

            holder.categoryCardView.setOnLongClickListener {
                selectOrUnselectPosition(position)
                return@setOnLongClickListener true
            }

            if(isPositionSelected(position))
            {
                holder.categoryCardView.setBackgroundColor(Color.YELLOW)
            }
            else
            {
                holder.categoryCardView.setBackgroundColor(holder.itemView.context.getColor(R.color.light_green))
            }
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
        lateinit var elementsCountTextView: TextView
        lateinit var cardView:CardView

        //If it's category item
        lateinit var categoryTittleTextView: TextView
        lateinit var summaryCostText: TextView
        lateinit var categoryCardView: CardView

        init {
            if(viewType == COST_ITEM_VIEW)
            {
                tittleTextView = itemView.findViewById(R.id.tittle_text_cost_item)
                costTextView = itemView.findViewById(R.id.cost_text_cost_item)
                elementsCountTextView = itemView.findViewById(R.id.elements_count_text_cost_item)
                cardView = itemView.findViewById(R.id.card_view_cost_item)
            }
            else
            {
                categoryTittleTextView = itemView.findViewById(R.id.tittle_text_category_item)
                summaryCostText = itemView.findViewById(R.id.summary_cost_text_category_item)
                categoryCardView = itemView.findViewById(R.id.card_view_category_item)
            }

        }
    }

    interface ItemOrCategoryButtonsClickListener
    {
        fun onCategoryCardClick(categoryEntity: CategoryEntity)
        fun onItemCardClick(costItemEntity: CostItemEntity)
    }
}