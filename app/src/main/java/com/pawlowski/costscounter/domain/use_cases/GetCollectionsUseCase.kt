package com.pawlowski.costscounter.domain.use_cases

import com.pawlowski.costscounter.CostsCollection
import com.pawlowski.costscounter.domain.CostCollectionsRepository
import javax.inject.Inject

class GetCollectionsUseCase @Inject constructor(private val repository: CostCollectionsRepository) {
    suspend fun execute(): List<CostsCollection>
    {
        return repository.getAllCollections()
    }

    suspend fun execute(collectionId: Int): CostsCollection
    {
        return repository.getCollection(collectionId)
    }
}