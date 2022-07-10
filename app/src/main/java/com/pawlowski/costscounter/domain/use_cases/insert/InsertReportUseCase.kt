package com.pawlowski.costscounter.domain.use_cases.insert

import com.pawlowski.costscounter.domain.CostReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertReportUseCase @Inject constructor(private val repository: CostReportRepository) {
    suspend fun execute(reportName: String, dateText: String)
    {
        withContext(Dispatchers.IO)
        {
            repository.createNewReport(reportName, dateText)
        }
    }
}