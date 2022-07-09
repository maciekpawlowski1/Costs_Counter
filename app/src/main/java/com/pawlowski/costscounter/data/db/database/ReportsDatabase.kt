package com.pawlowski.costscounter.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pawlowski.costscounter.data.db.daos.ReportsDao
import com.pawlowski.costscounter.data.entities.CategoryCostItemEntity
import com.pawlowski.costscounter.data.entities.CategoryEntity
import com.pawlowski.costscounter.data.entities.CostItemEntity
import com.pawlowski.costscounter.data.entities.ReportEntity

@Database(entities = [CategoryEntity::class, ReportEntity::class, CostItemEntity::class, CategoryCostItemEntity::class], version = 3)
abstract class ReportsDatabase: RoomDatabase() {
    abstract fun reportsDao(): ReportsDao
}