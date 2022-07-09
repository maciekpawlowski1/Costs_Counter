package com.pawlowski.costscounter.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ReportWithItemsAndCategories(
    @Embedded val reportEntity: ReportEntity,
    @Relation(
        parentColumn = "reportId",
        entityColumn = "reportId"
    )
    val costItems: List<CostItemEntity>,

    @Relation(
        entity = CategoryEntity::class,
        parentColumn = "reportId",
        entityColumn = "reportId"
    )
    val categoryItems: List<CategoryWithItems>
)
