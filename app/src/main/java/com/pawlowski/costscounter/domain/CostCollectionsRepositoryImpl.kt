package com.pawlowski.costscounter.domain

import com.pawlowski.costscounter.CostsCollection
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CostCollectionsRepositoryImpl @Inject constructor() : CostCollectionsRepository {

    override suspend fun getCollection(collectionId: Int): CostsCollection
    {
        TODO()
    }

    override suspend fun getAllCollections(): List<CostsCollection>
    {
        TODO()
    }

    override suspend fun createNewCollection(name: String): Int
    {
        TODO()
    }

    override suspend fun editCollection(collection: CostsCollection)
    {
        TODO()
    }

    override suspend fun deleteCollection(collectionId: Int)
    {
        TODO()
    }
}