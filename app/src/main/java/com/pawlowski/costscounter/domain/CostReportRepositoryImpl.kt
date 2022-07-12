package com.pawlowski.costscounter.domain

import com.pawlowski.costscounter.data.db.daos.ReportsDao
import com.pawlowski.costscounter.data.entities.*
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class CostReportRepositoryImpl @Inject constructor(
    private val reportsDao: ReportsDao
) : CostReportRepository {

    override fun getReport(reportId: Int): Flow<ReportWithItemsAndCategories>
    {
        return reportsDao.getReport(reportId)
    }

    override fun getAllReports(): Flow<List<ReportEntity>>
    {
        return reportsDao.getAllReports()
    }

    override fun getCategory(categoryId: Int): Flow<CategoryWithItems> {
        return reportsDao.getCategory(categoryId)
    }

    override suspend fun insertNewCategory(reportId: Int, name: String): Long {
        return reportsDao.insertNewCategory(CategoryEntity(0, reportId, name))
    }

    override suspend fun insertItemToCategory(categoryId: Int, name: String, cost: Double, amount: Int): Long {
        return reportsDao.insertCategoryItem(CategoryCostItemEntity(0, categoryId, name, cost, amount))
    }

    override suspend fun insertNewItem(reportId: Int, name: String, cost: Double, amount: Int) {
        reportsDao.insertItem(CostItemEntity(0, reportId, name, cost, amount))
    }

    override suspend fun deleteItem(costItemEntity: CostItemEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItems(items: List<CostItemEntity>) {
        reportsDao.deleteReportItems(items)
    }

    override suspend fun createNewReport(name: String, dateText: String)
    {
        reportsDao.createNewReport(ReportEntity(0, name, dateText))
    }

    override suspend fun editReport(report: ReportEntity)
    {
        TODO()
    }

    override suspend fun editCategory(categoryEntity: CategoryEntity) {
        reportsDao.editCategory(categoryEntity)
    }

    override suspend fun editItem(costItemEntity: CostItemEntity) {
        reportsDao.editItem(costItemEntity)
    }

    override suspend fun editItem(categoryCostItemEntity: CategoryCostItemEntity) {
        reportsDao.editItem(categoryCostItemEntity)
    }

    override suspend fun deleteReport(reportId: Int)
    {
        withContext(NonCancellable)
        {
            reportsDao.deleteReport(reportId)
            reportsDao.deleteCategoryItemsByReportId(reportId) //Has to be before deleteReportCategories
            reportsDao.deleteReportCategories(reportId)
            reportsDao.deleteReportItems(reportId)
            //TODO: Maybe change for auto cascade delete
        }
    }

    override suspend fun deleteCategories(categories: List<CategoryWithItems>) {
        withContext(NonCancellable)
        {
            for(el in categories)
            {
                reportsDao.deleteCategoryItems(el.categoryEntity.categoryId)
            }

            reportsDao.deleteCategories(categories.map { it.categoryEntity })
        }
        //TODO: Change for auto cascade delete
    }

    override suspend fun deleteCategoryItems(items: List<CategoryCostItemEntity>) {
        reportsDao.deleteCategoryItems(items)
    }
}