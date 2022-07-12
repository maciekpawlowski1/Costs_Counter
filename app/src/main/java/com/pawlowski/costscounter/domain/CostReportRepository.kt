package com.pawlowski.costscounter.domain

import com.pawlowski.costscounter.data.entities.*
import kotlinx.coroutines.flow.Flow

interface CostReportRepository {
    fun getReport(reportId: Int): Flow<ReportWithItemsAndCategories>

    fun getAllReports(): Flow<List<ReportEntity>>

    fun getCategory(categoryId: Int): Flow<CategoryWithItems>

    suspend fun insertNewCategory(reportId: Int, name: String): Long

    suspend fun insertItemToCategory(categoryId: Int, name: String, cost: Double, amount: Int): Long

    suspend fun insertNewItem(reportId: Int, name: String, cost: Double, amount: Int)



    suspend fun deleteItem(costItemEntity: CostItemEntity)

    suspend fun deleteItems(items: List<CostItemEntity>)

    suspend fun createNewReport(name: String, dateText: String)

    suspend fun editReport(report: ReportEntity)

    suspend fun editCategory(categoryEntity: CategoryEntity)

    suspend fun editItem(costItemEntity: CostItemEntity)

    suspend fun editItem(categoryCostItemEntity: CategoryCostItemEntity)

    suspend fun deleteReport(reportId: Int)

    suspend fun deleteCategories(categories: List<CategoryWithItems>)

    suspend fun deleteCategoryItems(items: List<CategoryCostItemEntity>)
}