package com.pawlowski.costscounter.domain

import com.pawlowski.costscounter.data.entities.ReportEntity
import com.pawlowski.costscounter.data.entities.ReportWithItemsAndCategories

interface CostReportRepository {
    suspend fun getReport(reportId: Int): ReportWithItemsAndCategories

    suspend fun getAllReports(): List<ReportEntity>

    /**
     * @return id of new collection
     */
    suspend fun createNewReport(name: String, dateText: String)

    suspend fun editReport(report: ReportEntity)

    suspend fun deleteReport(reportId: Int)
}