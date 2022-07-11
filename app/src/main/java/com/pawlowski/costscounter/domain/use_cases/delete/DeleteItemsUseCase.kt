package com.pawlowski.costscounter.domain.use_cases.delete

import com.pawlowski.costscounter.data.entities.CostItemEntity
import com.pawlowski.costscounter.domain.CostReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteItemsUseCase @Inject constructor(private val repository: CostReportRepository) {

    suspend fun execute(items: List<CostItemEntity>)
    {
        withContext(Dispatchers.IO)
        {
            repository.deleteItems(items)
        }
    }
}