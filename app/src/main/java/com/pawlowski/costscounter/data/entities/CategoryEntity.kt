package com.pawlowski.costscounter.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey val categoryId: Int,
    val reportId: Int,
    val categoryName: String
)