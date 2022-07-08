package com.pawlowski.costscounter.domain.use_cases

import com.pawlowski.costscounter.CostsReport
import com.pawlowski.costscounter.domain.CostReportRepository
import javax.inject.Inject

class GetReportUseCase @Inject constructor(private val repository: CostReportRepository) {
    suspend fun execute(): List<CostsReport>
    {
        return repository.getAllReports()
    }

    suspend fun execute(reportId: Int): CostsReport
    {
        return repository.getReport(reportId)
    }
}