package com.pawlowski.costscounter.presentation.report

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pawlowski.costscounter.data.entities.ReportEntity
import com.pawlowski.costscounter.R

class ReportsAdapter: RecyclerView.Adapter<ReportsAdapter.ReportsViewHolder>() {
    var reports: List<ReportEntity> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsViewHolder {
        return ReportsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.costs_report_item, parent, false))
    }

    override fun onBindViewHolder(holder: ReportsViewHolder, position: Int) {
        val currentReport = reports[position]
        holder.tittleTextView.text = currentReport.reportName
        holder.dateTextView.text = currentReport.dateText
    }

    override fun getItemCount(): Int {
        return reports.size
    }

    class ReportsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val tittleTextView = itemView.findViewById<TextView>(R.id.tittle_text_cost_report_item)
        val dateTextView = itemView.findViewById<TextView>(R.id.date_text_cost_report)
        val deleteButton = itemView.findViewById<ImageButton>(R.id.delete_button_cost_report_item)
    }
}