package com.pawlowski.costscounter.domain

import com.pawlowski.costscounter.CostsReport

interface CostReportRepository {
    suspend fun getReport(reportId: Int): CostsReport

    suspend fun getAllReports(): List<CostsReport>

    /**
     * @return id of new collection
     */
    suspend fun createNewReport(name: String): Int

    suspend fun editReport(report: CostsReport)

    suspend fun deleteReport(reportId: Int)
}