package com.pawlowski.costscounter.domain

import com.pawlowski.costscounter.data.db.daos.ReportsDao
import com.pawlowski.costscounter.data.entities.CostItemEntity
import com.pawlowski.costscounter.data.entities.ReportEntity
import com.pawlowski.costscounter.data.entities.ReportWithItemsAndCategories
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

    override suspend fun insertNewItem(reportId: Int, name: String, cost: Double) {
        reportsDao.insertItem(CostItemEntity(0, reportId, name, cost, amount = 1))
    }

    override suspend fun deleteItem(costItemEntity: CostItemEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun createNewReport(name: String, dateText: String)
    {
        reportsDao.createNewReport(ReportEntity(0, name, dateText))
    }

    override suspend fun editReport(report: ReportEntity)
    {
        TODO()
    }

    override suspend fun deleteReport(reportId: Int)
    {
        withContext(NonCancellable)
        {
            reportsDao.deleteReport(reportId)
            reportsDao.deleteReportCategories(reportId)
            reportsDao.deleteReportItems(reportId)
            //TODO: Delete Category Items
            //TODO: Maybe change for auto cascade delete
        }
    }
}