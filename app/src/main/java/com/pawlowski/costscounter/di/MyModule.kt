package com.pawlowski.costscounter.di

import com.pawlowski.costscounter.domain.CostReportRepository
import com.pawlowski.costscounter.domain.CostReportRepositoryImpl
import com.pawlowski.costscounter.excel_related.ExcelSaver
import com.pawlowski.costscounter.excel_related.ExcelSaverInDocumentsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MyModule {

    @ViewModelScoped
    @Provides
    fun costCollectionsRepository(costCollectionsRepositoryImpl: CostReportRepositoryImpl): CostReportRepository
    {
        return costCollectionsRepositoryImpl
    }

    @ViewModelScoped
    @Provides
    fun excelSaver(excelSaverInDocumentsImpl: ExcelSaverInDocumentsImpl): ExcelSaver
    {
        return excelSaverInDocumentsImpl
    }
}