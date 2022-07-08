package com.pawlowski.costscounter.domain.use_cases

import com.pawlowski.costscounter.domain.CostReportRepository
import javax.inject.Inject

class DeleteReportUseCase @Inject constructor(private val repository: CostReportRepository) {

    suspend fun execute(reportId: Int)
    {
        repository.deleteReport(reportId)
    }
}