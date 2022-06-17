package com.pawlowski.costscounter.di

import com.pawlowski.costscounter.domain.CostCollectionsRepository
import com.pawlowski.costscounter.domain.CostCollectionsRepositoryImpl
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
    fun costCollectionsRepository(costCollectionsRepositoryImpl: CostCollectionsRepositoryImpl): CostCollectionsRepository
    {
        return costCollectionsRepositoryImpl
    }
}