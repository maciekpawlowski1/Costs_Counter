package com.pawlowski.costscounter.domain

import com.pawlowski.costscounter.CostsCollection

interface CostCollectionsRepository {
    suspend fun getCollection(collectionId: Int): CostsCollection

    suspend fun getAllCollections(): List<CostsCollection>

    /**
     * @return id of new collection
     */
    suspend fun createNewCollection(name: String): Int

    suspend fun editCollection(collection: CostsCollection)

    suspend fun deleteCollection(collectionId: Int)
}