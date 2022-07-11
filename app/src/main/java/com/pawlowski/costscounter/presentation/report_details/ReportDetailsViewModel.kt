package com.pawlowski.costscounter.presentation.report_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pawlowski.costscounter.data.entities.CostItemEntity
import com.pawlowski.costscounter.domain.use_cases.delete.DeleteItemsUseCase
import com.pawlowski.costscounter.domain.use_cases.delete.DeleteReportUseCase
import com.pawlowski.costscounter.domain.use_cases.edit.EditReportUseCase
import com.pawlowski.costscounter.domain.use_cases.get.GetReportUseCase
import com.pawlowski.costscounter.domain.use_cases.insert.InsertCategoryUseCase
import com.pawlowski.costscounter.domain.use_cases.insert.InsertItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportDetailsViewModel @Inject constructor(
    private val getReportUseCase: GetReportUseCase,
    private val deleteItemsUseCase: DeleteItemsUseCase,
    private val editReportUseCase: EditReportUseCase,
    private val insertItemUseCase: InsertItemUseCase,
    private val insertCategoryUseCase: InsertCategoryUseCase,
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
            deleteItemsUseCase.execute(items)
        }
    }
}