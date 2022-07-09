package com.pawlowski.costscounter.domain.use_cases

import com.pawlowski.costscounter.data.entities.ReportEntity
import com.pawlowski.costscounter.data.entities.ReportWithItemsAndCategories
import com.pawlowski.costscounter.domain.CostReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetReportUseCase @Inject constructor(private val repository: CostReportRepository) {
    suspend fun execute(): List<ReportEntity>
    {
        return withContext(Dispatchers.IO)
        {
            return@withContext repository.getAllReports()
        }
    }

    suspend fun execute(reportId: Int): ReportWithItemsAndCategories
    {
        return withContext(Dispatchers.IO)
        {
            return@withContext repository.getReport(reportId)
        }
    }
}