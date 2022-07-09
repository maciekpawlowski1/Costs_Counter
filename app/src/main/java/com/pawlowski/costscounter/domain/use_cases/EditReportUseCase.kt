package com.pawlowski.costscounter.domain.use_cases

import com.pawlowski.costscounter.data.entities.ReportEntity
import com.pawlowski.costscounter.domain.CostReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EditReportUseCase @Inject constructor(private val repository: CostReportRepository) {
    suspend fun execute(report: ReportEntity)
    {
        withContext(Dispatchers.IO)
        {
            repository.editReport(report)
        }
    }
}