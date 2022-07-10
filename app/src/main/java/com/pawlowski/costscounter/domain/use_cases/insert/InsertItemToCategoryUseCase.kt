package com.pawlowski.costscounter.domain.use_cases.insert

import com.pawlowski.costscounter.domain.CostReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertItemToCategoryUseCase @Inject constructor(private val repository: CostReportRepository) {

    suspend fun execute(categoryId: Int, name: String, cost: Double, amount: Int): Long
    {
        return withContext(Dispatchers.IO)
        {
            return@withContext repository.insertItemToCategory(categoryId, name, cost, amount)
        }
    }
}