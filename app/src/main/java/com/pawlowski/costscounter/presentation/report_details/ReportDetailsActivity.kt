package com.pawlowski.costscounter.presentation.report_details

import android.content.Context
import android.content.Intent
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
import com.pawlowski.costscounter.data.entities.CategoryEntity
import com.pawlowski.costscounter.presentation.category.CategoryActivity
import com.pawlowski.costscounter.presentation.report_details.dialogs.DialogWithOneEditText
import com.pawlowski.costscounter.presentation.report_details.dialogs.AddItemDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReportDetailsActivity: AppCompatActivity(), AddItemDialog.AddItemDialogButtonsClickListener,
    ReportItemsAdapter.ItemOrCategoryButtonsClickListener,
    DialogWithOneEditText.OnDialogButtonsClickListener {

    private val viewModel by viewModels<ReportDetailsViewModel>()

    private val adapter: ReportItemsAdapter = ReportItemsAdapter(this)
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
            adapter.resetItemsAndCategories(it.costItems, it.categoryItems)
        }


    }


    private fun onAddButtonClick(v: View)
    {
        val dialog = AddItemDialog(this)
        dialog.show(supportFragmentManager, "addItemDialog")
    }

    private fun onAddCategoryClick(item: MenuItem)
    {
        val dialog = DialogWithOneEditText(this)
        dialog.show(supportFragmentManager, "AddCategoryDialog")
    }

    private fun onAddFromTemplateClick(item:MenuItem)
    {
        TODO("Not implemented yet")
    }

    private fun onShareButtonClick(item: MenuItem)
    {
        viewModel.saveToExcel()
    }

    private fun onDeleteButtonClick(item: MenuItem)
    {
        val selectedItems = adapter.getSelectedItems()
        val selectedCategories = adapter.getSelectedCategories()
        adapter.unselectAll()

        if(selectedItems.isNotEmpty())
        {
            viewModel.deleteItems(selectedItems)
        }

        if(selectedCategories.isNotEmpty())
        {
            viewModel.deleteCategories(selectedCategories)
        }
    }

    override fun onConfirmButtonClickInDialog(name: String) {
        viewModel.insertNewCategory(name)
    }


    override fun onAddButtonInDialogClick(name: String, cost: Double, amount: Int) {
        viewModel.insertNewItem(name, cost, amount)
    }





    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.report_details_menu, menu)

        val shareButton = menu.findItem(R.id.share_button_report_details_menu)
        val createCategoryButton = menu.findItem(R.id.add_category_button_report_details_menu)
        val addFromTemplateButton = menu.findItem(R.id.add_from_template_button_report_details_menu)

        val deleteButton = menu.findItem(R.id.delete_button_report_details_menu)

        adapter.isSomethingSelectedLiveData.observe(this)
        {
            shareButton.isVisible = !it
            createCategoryButton.isVisible = !it
            addFromTemplateButton.isVisible = !it

            deleteButton.isVisible = it
        }
        return true
    }

    override fun onBackPressed() {
        if(adapter.isSomethingSelected())
        {
            adapter.unselectAll()
        }
        else
        {
            super.onBackPressed()
        }
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
            android.R.id.home ->
            {
                onBackPressed()
                return true
            }
            R.id.delete_button_report_details_menu ->
            {
                onDeleteButtonClick(item)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun launch(context: Context, reportId: Int)
        {
            val i = Intent(context, ReportDetailsActivity::class.java)
            i.putExtra("reportId", reportId)
            context.startActivity(i)
        }
    }

    override fun onCategoryCardClick(categoryEntity: CategoryEntity) {
        CategoryActivity.launch(this, categoryEntity.categoryId)
    }
}