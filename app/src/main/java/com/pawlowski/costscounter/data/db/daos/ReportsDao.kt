package com.pawlowski.costscounter.data.db.daos

import androidx.room.*
import com.pawlowski.costscounter.data.entities.*
import kotlinx.coroutines.flow.Flow


@Dao
interface ReportsDao {

    @Insert
    suspend fun createNewReport(reportEntity: ReportEntity)

    @Insert
    suspend fun insertItem(costItemEntity: CostItemEntity)

    @Insert
    suspend fun insertCategoryItem(categoryCostItemEntity: CategoryCostItemEntity): Long

    @Insert
    suspend fun insertNewCategory(categoryEntity: CategoryEntity): Long

    @Transaction
    @Query("SELECT * FROM ReportEntity WHERE reportId = :reportId")
    fun getReport(reportId: Int): Flow<ReportWithItemsAndCategories>

    @Transaction
    @Query("SELECT * FROM CategoryEntity WHERE categoryId = :categoryId")
    fun getCategory(categoryId: Int): Flow<CategoryWithItems>

    @Query("SELECT * FROM ReportEntity")
    fun getAllReports(): Flow<List<ReportEntity>>

    @Query("DELETE FROM ReportEntity WHERE reportId = :reportId")
    suspend fun deleteReport(reportId: Int)

    @Query("DELETE FROM CostItemEntity WHERE reportId = :reportId")
    suspend fun deleteReportItems(reportId: Int)

    @Delete
    suspend fun deleteReportItems(items: List<CostItemEntity>)

    @Query("DELETE FROM CategoryEntity WHERE reportId = :reportId")
    suspend fun deleteReportCategories(reportId: Int)

    @Query("DELETE FROM CategoryCostItemEntity WHERE categoryId = :categoryId")
    suspend fun deleteCategoryItems(categoryId: Int)

    @Update
    suspend fun editCategory(categoryEntity: CategoryEntity)
}