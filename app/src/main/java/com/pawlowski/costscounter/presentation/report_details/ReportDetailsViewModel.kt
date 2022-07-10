package com.pawlowski.costscounter.presentation.report_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pawlowski.costscounter.domain.use_cases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportDetailsViewModel @Inject constructor(
    private val getReportUseCase: GetReportUseCase,
    private val deleteReportUseCase: DeleteReportUseCase,
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
}