package com.pawlowski.costscounter.domain.use_cases.get

import com.pawlowski.costscounter.data.entities.ReportEntity
import com.pawlowski.costscounter.data.entities.ReportWithItemsAndCategories
import com.pawlowski.costscounter.domain.CostReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetReportUseCase @Inject constructor(private val repository: CostReportRepository) {
    fun execute(): Flow<List<ReportEntity>>
    {
        return repository.getAllReports()
    }

    fun execute(reportId: Int): Flow<ReportWithItemsAndCategories>
    {
        return repository.getReport(reportId)
    }
}