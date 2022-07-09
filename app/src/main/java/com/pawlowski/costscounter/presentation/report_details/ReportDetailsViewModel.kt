package com.pawlowski.costscounter.presentation.report_details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pawlowski.costscounter.domain.use_cases.DeleteReportUseCase
import com.pawlowski.costscounter.domain.use_cases.EditReportUseCase
import com.pawlowski.costscounter.domain.use_cases.GetReportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportDetailsViewModel @Inject constructor(
    private val getReportUseCase: GetReportUseCase,
    private val deleteReportUseCase: DeleteReportUseCase,
    private val editReportUseCase: EditReportUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    val report = savedStateHandle.get<Int>("reportId")!!.let {
        return@let getReportUseCase.execute(it).asLiveData()
    }

}