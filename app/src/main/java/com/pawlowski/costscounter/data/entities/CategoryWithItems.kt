package com.pawlowski.costscounter.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithItems(
    @Embedded val categoryEntity: CategoryEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "categoryId"
    )
    val items: List<CategoryCostItemEntity>
)
