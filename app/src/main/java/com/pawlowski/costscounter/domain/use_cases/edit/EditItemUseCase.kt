package com.pawlowski.costscounter.domain.use_cases.edit

import com.pawlowski.costscounter.data.entities.CostItemEntity
import com.pawlowski.costscounter.domain.CostReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EditItemUseCase @Inject constructor(private val repository: CostReportRepository) {

    suspend fun execute(costItemEntity: CostItemEntity)
    {
        withContext(Dispatchers.IO)
        {
            repository.editItem(costItemEntity)
        }
    }
}