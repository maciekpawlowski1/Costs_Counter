package com.pawlowski.costscounter.domain.use_cases.get

import com.pawlowski.costscounter.data.entities.CategoryWithItems
import com.pawlowski.costscounter.domain.CostReportRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(private val repository: CostReportRepository) {
    fun execute(categoryId: Int): Flow<CategoryWithItems>
    {
        return repository.getCategory(categoryId)
    }
}