package com.pawlowski.costscounter.domain

import com.pawlowski.costscounter.data.entities.CategoryWithItems
import com.pawlowski.costscounter.data.entities.CostItemEntity
import com.pawlowski.costscounter.data.entities.ReportEntity
import com.pawlowski.costscounter.data.entities.ReportWithItemsAndCategories
import kotlinx.coroutines.flow.Flow

interface CostReportRepository {
    fun getReport(reportId: Int): Flow<ReportWithItemsAndCategories>

    fun getAllReports(): Flow<List<ReportEntity>>

    fun getCategory(categoryId: Int): Flow<CategoryWithItems>

    suspend fun insertNewCategory(reportId: Int, name: String): Long

    suspend fun insertItemToCategory(categoryId: Int, name: String, cost: Double, amount: Int): Long

    suspend fun insertNewItem(reportId: Int, name: String, cost: Double, amount: Int)



    suspend fun deleteItem(costItemEntity: CostItemEntity)

    suspend fun createNewReport(name: String, dateText: String)

    suspend fun editReport(report: ReportEntity)

    suspend fun deleteReport(reportId: Int)
}