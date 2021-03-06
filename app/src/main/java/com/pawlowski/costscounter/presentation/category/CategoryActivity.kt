package com.pawlowski.costscounter.presentation.category

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.pawlowski.costscounter.R
import com.pawlowski.costscounter.data.entities.CategoryCostItemEntity
import com.pawlowski.costscounter.data.entities.CategoryEntity
import com.pawlowski.costscounter.data.entities.CostItemEntity
import com.pawlowski.costscounter.presentation.report_details.ReportItemsAdapter
import com.pawlowski.costscounter.presentation.report_details.dialogs.DialogWithOneEditText
import com.pawlowski.costscounter.presentation.report_details.dialogs.AddOrEditItemDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryActivity: AppCompatActivity(), AddOrEditItemDialog.AddItemDialogButtonsClickListener,
    ReportItemsAdapter.ItemOrCategoryButtonsClickListener,
    DialogWithOneEditText.OnDialogButtonsClickListener {

    private lateinit var recycler: RecyclerView
    private lateinit var addButton: FloatingActionButton
    private val adapter = ReportItemsAdapter(this)
    private lateinit var categoryNameText: TextInputEditText
    private lateinit var editNameButton: ImageButton

    private val viewModel by viewModels<CategoryActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        recycler = findViewById(R.id.recycler_category_activity)
        addButton = findViewById(R.id.add_item_button_category_activity)
        categoryNameText = findViewById(R.id.name_input_category_activity)
        editNameButton = findViewById(R.id.edit_button_category_activity)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter


        addButton.setOnClickListener(this::onAddButtonClick)
        editNameButton.setOnClickListener(this::onEditNameButtonClick)

        viewModel.category.observe(this)
        { fetchedItems ->
            val reportId = fetchedItems.categoryEntity.reportId
            adapter.resetItemsAndCategories(fetchedItems.items.map { CostItemEntity(it.categoryItemId, reportId, it.name, it.cost, it.amount) }, listOf())
            categoryNameText.setText(fetchedItems.categoryEntity.categoryName)
        }
    }

    private fun onAddButtonClick(v: View)
    {
        val dialog = AddOrEditItemDialog(this)
        dialog.show(supportFragmentManager, "addItemDialog")
    }

    private fun onEditNameButtonClick(v: View)
    {
       val dialog = DialogWithOneEditText(this, "Edit", viewModel.category.value?.categoryEntity?.categoryName, dialogTittle = "Edit category name")
        dialog.show(supportFragmentManager, "EditDialog")
    }

    private fun onDeleteButtonClick(item: MenuItem)
    {
        val selected = adapter.getSelectedItems()
        adapter.unselectAll()
        viewModel.deleteItems(selected.map { CategoryCostItemEntity(it.itemId, viewModel.category.value!!.categoryEntity.categoryId, it.name, it.cost, it.amount) })
    }

    override fun onConfirmButtonInAddEditItemDialogClick(name: String, cost: Double, amount: Int) {
        viewModel.insertItemToCategory(name, cost, amount)
    }

    override fun onConfirmButtonClickInDialog(name: String) {
        viewModel.editCategoryName(name)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.category_menu, menu)

        val deleteButton = menu.findItem(R.id.delete_button_category_menu)
        adapter.isSomethingSelectedLiveData.observe(this)
        {
            deleteButton.isVisible = it
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            android.R.id.home ->
            {
                onBackPressed()
                return true
            }
            R.id.delete_button_category_menu ->
            {
                onDeleteButtonClick(item)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun launch(context: Context, categoryId: Int)
        {
            val i = Intent(context, CategoryActivity::class.java)
            i.putExtra("categoryId", categoryId)
            context.startActivity(i)
        }
    }
    override fun onCategoryCardClick(categoryEntity: CategoryEntity) {} //No categories here


    override fun onItemCardClick(costItemEntity: CostItemEntity) {
        val dialog = AddOrEditItemDialog(object: AddOrEditItemDialog.AddItemDialogButtonsClickListener
        {
            override fun onConfirmButtonInAddEditItemDialogClick(
                name: String,
                cost: Double,
                amount: Int
            ) {
                viewModel.editItem(CategoryCostItemEntity(costItemEntity.itemId, viewModel.category.value!!.categoryEntity.categoryId, name, cost, amount))
            }

        }, "Edit", costItemEntity.name, costItemEntity.cost, costItemEntity.amount)
        dialog.show(supportFragmentManager, "editItemDialog")
    }
}