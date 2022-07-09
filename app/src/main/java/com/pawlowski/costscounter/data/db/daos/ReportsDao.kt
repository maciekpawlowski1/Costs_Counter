package com.pawlowski.costscounter.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pawlowski.costscounter.data.entities.*

@Dao
interface ReportsDao {

    @Insert
    suspend fun createNewReport(reportEntity: ReportEntity)

    @Insert
    suspend fun insertItem(costItemEntity: CostItemEntity)

    @Insert
    suspend fun insertCategoryItem(categoryCostItemEntity: CategoryCostItemEntity)

    @Insert fun insertNewCategory(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM ReportEntity WHERE reportId = :reportId")
    suspend fun getReport(reportId: Int): ReportWithItemsAndCategories

    @Query("SELECT * FROM ReportEntity")
    suspend fun getAllReports(): List<ReportEntity>
}