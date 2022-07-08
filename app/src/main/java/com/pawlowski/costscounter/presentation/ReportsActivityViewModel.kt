package com.pawlowski.costscounter.presentation

import androidx.lifecycle.ViewModel
import com.pawlowski.costscounter.domain.use_cases.DeleteReportUseCase
import com.pawlowski.costscounter.domain.use_cases.GetReportUseCase
import com.pawlowski.costscounter.domain.use_cases.InsertReportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ReportsActivityViewModel @Inject constructor(
    private val getReportUseCase: GetReportUseCase,
    private val insertReportUseCase: InsertReportUseCase,
    private val deleteReportUseCase: DeleteReportUseCase
    ): ViewModel() {

}