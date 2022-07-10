package com.pawlowski.costscounter.domain.use_cases

import com.pawlowski.costscounter.domain.CostReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertCategoryUseCase@Inject constructor(private val repository: CostReportRepository) {

    suspend fun execute(reportId: Int, name: String): Long
    {
        return withContext(Dispatchers.IO)
        {
            return@withContext repository.insertNewCategory(reportId, name)
        }
    }
}