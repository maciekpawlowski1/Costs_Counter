package com.pawlowski.costscounter.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryCostItemEntity(
    @PrimaryKey val categoryItemId: Int,
    val categoryId: Int,
    val name: String,
    val cost: Double,
    val amount: Int
)
