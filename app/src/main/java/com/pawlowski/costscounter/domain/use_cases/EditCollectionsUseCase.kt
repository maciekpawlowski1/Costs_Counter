package com.pawlowski.costscounter.domain.use_cases

import com.pawlowski.costscounter.CostsCollection
import com.pawlowski.costscounter.domain.CostCollectionsRepository
import javax.inject.Inject

class EditCollectionsUseCase @Inject constructor(private val repository: CostCollectionsRepository) {
    suspend fun execute(collection: CostsCollection)
    {
        repository.editCollection(collection)
    }
}