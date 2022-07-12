package com.pawlowski.costscounter.domain.use_cases.delete

import com.pawlowski.costscounter.data.entities.CategoryWithItems
import com.pawlowski.costscounter.domain.CostReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteCategoriesUseCase @Inject constructor(private val repository: CostReportRepository) {

    suspend fun execute(categories: List<CategoryWithItems>)
    {
        withContext(Dispatchers.IO)
        {
            repository.deleteCategories(categories)
        }
    }
}