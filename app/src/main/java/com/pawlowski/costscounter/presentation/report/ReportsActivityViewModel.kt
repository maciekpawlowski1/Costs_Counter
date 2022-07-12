package com.pawlowski.costscounter.presentation.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pawlowski.costscounter.data.entities.ReportEntity
import com.pawlowski.costscounter.domain.use_cases.delete.DeleteReportUseCase
import com.pawlowski.costscounter.domain.use_cases.get.GetReportUseCase
import com.pawlowski.costscounter.domain.use_cases.insert.InsertReportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class ReportsActivityViewModel @Inject constructor(
    private val getReportUseCase: GetReportUseCase,
    private val insertReportUseCase: InsertReportUseCase,
    private val deleteReportUseCase: DeleteReportUseCase
    ): ViewModel() {

    val reports: LiveData<List<ReportEntity>> = getReportUseCase.execute().asLiveData()


    fun insertNewReport(name: String)
    {
        viewModelScope.launch {
            val calendar = Calendar.getInstance()
            val day = if(calendar.get(Calendar.DAY_OF_MONTH) < 10)
                "0${calendar.get(Calendar.DAY_OF_MONTH)}"
            else
                "${calendar.get(Calendar.DAY_OF_MONTH)}"

            val month = if(calendar.get(Calendar.MONTH) < 10)
                "0${calendar.get(Calendar.MONTH)}"
                else
                    "${calendar.get(Calendar.MONTH)}"

            val dateText = "${day}.${month}.${calendar.get(Calendar.YEAR)}"
            insertReportUseCase.execute(name, dateText)
        }
    }

    fun deleteReport(reportEntity: ReportEntity)
    {
        viewModelScope.launch {
            deleteReportUseCase.execute(reportEntity.reportId)
        }
    }



}