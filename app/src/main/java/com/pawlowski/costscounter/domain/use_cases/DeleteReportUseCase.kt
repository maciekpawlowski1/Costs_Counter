package com.pawlowski.costscounter.domain.use_cases

import com.pawlowski.costscounter.domain.CostReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteReportUseCase @Inject constructor(private val repository: CostReportRepository) {

    suspend fun execute(reportId: Int)
    {
        withContext(Dispatchers.IO)
        {
            repository.deleteReport(reportId)
        }
    }
}