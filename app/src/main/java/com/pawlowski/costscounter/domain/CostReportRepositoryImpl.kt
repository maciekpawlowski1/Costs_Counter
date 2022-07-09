package com.pawlowski.costscounter.domain

import com.pawlowski.costscounter.data.db.daos.ReportsDao
import com.pawlowski.costscounter.data.entities.ReportEntity
import com.pawlowski.costscounter.data.entities.ReportWithItemsAndCategories
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CostReportRepositoryImpl @Inject constructor(
    private val reportsDao: ReportsDao
) : CostReportRepository {

    override suspend fun getReport(reportId: Int): ReportWithItemsAndCategories
    {
        return reportsDao.getReport(reportId)
    }

    override suspend fun getAllReports(): List<ReportEntity>
    {
        return reportsDao.getAllReports()
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
        TODO()
    }
}