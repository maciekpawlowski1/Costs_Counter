package com.pawlowski.costscounter.presentation.report_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pawlowski.costscounter.data.entities.CategoryWithItems
import com.pawlowski.costscounter.data.entities.CostItemEntity
import com.pawlowski.costscounter.domain.use_cases.delete.DeleteCategoriesUseCase
import com.pawlowski.costscounter.domain.use_cases.delete.DeleteItemsUseCase
import com.pawlowski.costscounter.domain.use_cases.edit.EditReportUseCase
import com.pawlowski.costscounter.domain.use_cases.get.GetReportUseCase
import com.pawlowski.costscounter.domain.use_cases.insert.InsertCategoryUseCase
import com.pawlowski.costscounter.domain.use_cases.insert.InsertItemUseCase
import com.pawlowski.costscounter.excel_related.ExcelGeneratorFromReportClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ReportDetailsViewModel @Inject constructor(
    private val getReportUseCase: GetReportUseCase,
    private val deleteItemsUseCase: DeleteItemsUseCase,
    private val deleteCategoriesUseCase: DeleteCategoriesUseCase,
    private val editReportUseCase: EditReportUseCase,
    private val insertItemUseCase: InsertItemUseCase,
    private val insertCategoryUseCase: InsertCategoryUseCase,
    private val excelGeneratorFromReportClass: ExcelGeneratorFromReportClass,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val reportId = savedStateHandle.get<Int>("reportId")!!

    val report = getReportUseCase.execute(reportId).asLiveData()


    fun insertNewItem(name: String, cost: Double, amount:Int)
    {
        viewModelScope.launch {
            insertItemUseCase.execute(reportId, name, cost, amount)
        }
    }

    fun insertNewCategory(name: String)
    {
        viewModelScope.launch {
            insertCategoryUseCase.execute(reportId, name)
        }
    }

    fun deleteItems(items: List<CostItemEntity>)
    {
        viewModelScope.launch {
            deleteItemsUseCase.executeNormalItems(items)
        }
    }

    fun deleteCategories(categories : List<CategoryWithItems>)
    {
        viewModelScope.launch {
            deleteCategoriesUseCase.execute(categories)
        }
    }

    fun saveToExcel()
    {
        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                report.value?.let {
                    excelGeneratorFromReportClass.createExcelFromReportEntity(it)
                }
            }
        }

    }
}