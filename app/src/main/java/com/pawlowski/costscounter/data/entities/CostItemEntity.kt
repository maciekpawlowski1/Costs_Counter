package com.pawlowski.costscounter.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CostItemEntity(
    @PrimaryKey val itemId: Int,
    val reportId: Int,
    val name: String,
    val cost: Double,
    val amount: Int
)