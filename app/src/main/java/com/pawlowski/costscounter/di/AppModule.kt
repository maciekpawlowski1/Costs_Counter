package com.pawlowski.costscounter.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.pawlowski.costscounter.data.db.daos.ReportsDao
import com.pawlowski.costscounter.data.db.database.ReportsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun context(application: Application): Context
    {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun reportsDatabase(appContext: Context): ReportsDatabase
    {
        return Room.databaseBuilder(appContext,
                ReportsDatabase::class.java, "reports-database"
            ).fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }

    @Singleton
    @Provides
    fun reportsDao(reportsDatabase: ReportsDatabase): ReportsDao
    {
        return reportsDatabase.reportsDao()
    }
}