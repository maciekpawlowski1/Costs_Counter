package com.pawlowski.costscounter.presentation.report_details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pawlowski.costscounter.R
import com.pawlowski.costscounter.presentation.report_details.dialogs.AddItemDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReportDetailsActivity: AppCompatActivity(), AddItemDialog.AddItemDialogButtonsClickListener {

    private val viewModel by viewModels<ReportDetailsViewModel>()

    private val adapter: ReportItemsAdapter = ReportItemsAdapter()
    private lateinit var recycler: RecyclerView
    private lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_details)

        addButton = findViewById(R.id.add_cost_item_button_report_details)
        recycler = findViewById(R.id.recycler_report_details)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        addButton.setOnClickListener(this::onAddButtonClick)

        viewModel.report.observe(this)
        {
            adapter.items = it.costItems
        }


    }


    private fun onAddButtonClick(v: View)
    {
        val dialog = AddItemDialog(this)
        dialog.show(supportFragmentManager, "addItemDialog")
    }

    private fun onAddCategoryClick(item: MenuItem)
    {
        TODO("Not implemented yet")
    }

    private fun onAddFromTemplateClick(item:MenuItem)
    {
        TODO("Not implemented yet")
    }

    private fun onShareButtonClick(item: MenuItem)
    {
        TODO("Not implemented yet")
    }

    override fun onAddButtonInDialogClick(name: String, cost: Double, amount: Int) {
        viewModel.insertNewItem(name, cost, amount)
    }






    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.report_details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.share_button_report_details_menu ->
            {
                onShareButtonClick(item)
                return true
            }
            R.id.add_category_button_report_details_menu ->
            {
                onAddCategoryClick(item)
                return true
            }
            R.id.add_from_template_button_report_details_menu ->
            {
                onAddFromTemplateClick(item)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}