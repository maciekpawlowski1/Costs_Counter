package com.pawlowski.costscounter.presentation.report_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pawlowski.costscounter.R
import com.pawlowski.costscounter.data.entities.CostItemEntity

class ReportItemsAdapter: RecyclerView.Adapter<ReportItemsAdapter.ReportItemHolder>() {

    var items: List<CostItemEntity> = listOf()
        set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportItemHolder {
        return ReportItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.cost_item, parent, false))
    }

    override fun onBindViewHolder(holder: ReportItemHolder, position: Int) {
        val currentItem = items[position]
        holder.tittleTextView.text = currentItem.name
        holder.costTextView.text = "${currentItem.cost}"
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ReportItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val tittleTextView: TextView = itemView.findViewById(R.id.tittle_text_cost_item)
        val costTextView: TextView = itemView.findViewById(R.id.cost_text_cost_item)
    }
}