package com.pawlowski.costscounter.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReportEntity(
    @PrimaryKey val reportId: Int,
    val reportName: String,
    val dateText: String
)