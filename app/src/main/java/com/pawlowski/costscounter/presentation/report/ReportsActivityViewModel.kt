package com.pawlowski.costscounter.presentation.report

import androidx.lifecycle.*
import com.pawlowski.costscounter.data.entities.ReportEntity
import com.pawlowski.costscounter.domain.use_cases.DeleteReportUseCase
import com.pawlowski.costscounter.domain.use_cases.GetReportUseCase
import com.pawlowski.costscounter.domain.use_cases.InsertReportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
        //TODO: Change date
        viewModelScope.launch {
            insertReportUseCase.execute(name, "testDate")
        }
    }

    fun deleteReport(reportEntity: ReportEntity)
    {
        viewModelScope.launch {
            deleteReportUseCase.execute(reportEntity.reportId)
        }
    }

}