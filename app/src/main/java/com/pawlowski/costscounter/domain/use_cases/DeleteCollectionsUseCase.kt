package com.pawlowski.costscounter.domain.use_cases

import com.pawlowski.costscounter.domain.CostCollectionsRepository
import javax.inject.Inject

class DeleteCollectionsUseCase @Inject constructor(private val repository: CostCollectionsRepository) {

    suspend fun execute(collectionId: Int)
    {
        repository.deleteCollection(collectionId)
    }
}