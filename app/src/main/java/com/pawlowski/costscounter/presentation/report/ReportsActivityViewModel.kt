package com.pawlowski.costscounter.presentation.report

import androidx.lifecycle.*
import com.pawlowski.costscounter.CostsReport
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

    val reports: LiveData<List<CostsReport>> get() = reports_

    private val reports_: MutableLiveData<List<CostsReport>> by lazy {
        MutableLiveData()
    }

    fun insertNewReport(name: String)
    {
        viewModelScope.launch {
            val id = insertReportUseCase.execute(name)
            reports_.value = getReportUseCase.execute()
        }
    }

    init {
        viewModelScope.launch {
            reports_.value = getReportUseCase.execute()
        }

    }
}