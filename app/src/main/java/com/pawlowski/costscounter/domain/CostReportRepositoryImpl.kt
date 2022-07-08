package com.pawlowski.costscounter.domain

import com.pawlowski.costscounter.CostsReport
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CostReportRepositoryImpl @Inject constructor() : CostReportRepository {

    override suspend fun getReport(reportId: Int): CostsReport
    {
        TODO()
    }

    override suspend fun getAllReports(): List<CostsReport>
    {
        TODO()
    }

    override suspend fun createNewReport(name: String): Int
    {
        TODO()
    }

    override suspend fun editReport(report: CostsReport)
    {
        TODO()
    }

    override suspend fun deleteReport(reportId: Int)
    {
        TODO()
    }
}