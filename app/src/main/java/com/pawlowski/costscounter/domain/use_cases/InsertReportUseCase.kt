package com.pawlowski.costscounter.domain.use_cases

import com.pawlowski.costscounter.domain.CostReportRepository
import javax.inject.Inject

class InsertReportUseCase @Inject constructor(private val repository: CostReportRepository) {
    suspend fun execute(reportName: String, dateText: String)
    {
        repository.createNewReport(reportName, dateText)
    }
}