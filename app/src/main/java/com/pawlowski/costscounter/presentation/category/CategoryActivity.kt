package com.pawlowski.costscounter.presentation.category

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pawlowski.costscounter.R
import com.pawlowski.costscounter.presentation.report_details.ReportItemsAdapter
import com.pawlowski.costscounter.presentation.report_details.dialogs.AddItemDialog

class CategoryActivity: AppCompatActivity(), AddItemDialog.AddItemDialogButtonsClickListener {

    private lateinit var recycler: RecyclerView
    private lateinit var addButton: FloatingActionButton
    private val adapter = ReportItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        recycler = findViewById(R.id.recycler_category_activity)
        addButton = findViewById(R.id.add_item_button_category_activity)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter


        addButton.setOnClickListener(this::onAddButtonClick)
    }

    private fun onAddButtonClick(v: View)
    {
        val dialog = AddItemDialog(this)
        dialog.show(supportFragmentManager, "addItemDialog")
    }

    override fun onAddButtonInDialogClick(name: String, cost: Double, amount: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        fun launch(context: Context, categoryId: Int)
        {
            val i = Intent(context, CategoryActivity::class.java)
            i.putExtra("categoryId", categoryId)
            context.startActivity(i)
        }
    }
}