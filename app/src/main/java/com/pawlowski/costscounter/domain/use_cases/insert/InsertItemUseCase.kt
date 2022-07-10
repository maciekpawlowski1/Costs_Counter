package com.pawlowski.costscounter.domain.use_cases.insert

import com.pawlowski.costscounter.domain.CostReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertItemUseCase@Inject constructor(private val repository: CostReportRepository) {

    suspend fun execute(reportId: Int, name: String, cost: Double, amount: Int)
    {
        withContext(Dispatchers.IO)
        {
            repository.insertNewItem(reportId, name, cost, amount)
        }
    }
}