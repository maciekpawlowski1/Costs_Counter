package com.pawlowski.costscounter.domain.use_cases

import com.pawlowski.costscounter.CostsReport
import com.pawlowski.costscounter.domain.CostReportRepository
import javax.inject.Inject

class EditReportUseCase @Inject constructor(private val repository: CostReportRepository) {
    suspend fun execute(report: CostsReport)
    {
        repository.editReport(report)
    }
}