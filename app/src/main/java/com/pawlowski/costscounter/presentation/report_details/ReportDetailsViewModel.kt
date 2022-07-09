package com.pawlowski.costscounter.presentation.report_details

import androidx.lifecycle.ViewModel
import com.pawlowski.costscounter.domain.use_cases.DeleteReportUseCase
import com.pawlowski.costscounter.domain.use_cases.EditReportUseCase
import com.pawlowski.costscounter.domain.use_cases.GetReportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportDetailsViewModel @Inject constructor(
    private val getReportUseCase: GetReportUseCase,
    private val deleteReportUseCase: DeleteReportUseCase,
    private val editReportUseCase: EditReportUseCase
): ViewModel() {

}