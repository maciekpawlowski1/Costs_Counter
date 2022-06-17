package com.pawlowski.costscounter.domain.use_cases

import com.pawlowski.costscounter.domain.CostCollectionsRepository
import javax.inject.Inject

class InsertCollectionsUseCase @Inject constructor(private val repository: CostCollectionsRepository) {
    suspend fun execute(collectionName: String): Int
    {
        return repository.createNewCollection(collectionName)
    }
}