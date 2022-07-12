package com.pawlowski.costscounter.domain.use_cases.edit

import com.pawlowski.costscounter.data.entities.CategoryCostItemEntity
import com.pawlowski.costscounter.data.entities.CategoryEntity
import com.pawlowski.costscounter.domain.CostReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EditCategoryUseCase @Inject constructor(private val reportRepository: CostReportRepository) {

    suspend fun execute(categoryEntity: CategoryEntity)
    {
        withContext(Dispatchers.IO)
        {
            reportRepository.editCategory(categoryEntity)
        }
    }

    suspend fun execute(categoryCostItemEntity: CategoryCostItemEntity)
    {
        withContext(Dispatchers.IO)
        {
            reportRepository.editItem(categoryCostItemEntity)
        }
    }
}