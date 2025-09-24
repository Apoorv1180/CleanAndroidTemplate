package com.androidcleantemplate.core.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.androidcleantemplate.core.database.dao.SampleDao
import com.androidcleantemplate.core.database.entity.SampleEntity

/**
 * Main database class for the application.
 * 
 * This database follows Clean Architecture principles and provides
 * a centralized data storage solution using Room.
 * 
 * @param entities List of all database entities
 * @param version Database version for migrations
 * @param exportSchema Whether to export schema for testing
 */
@Database(
    entities = [
        SampleEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CleanAndroidTemplateDatabase : RoomDatabase() {

    /**
     * Provides access to sample data operations
     */
    abstract fun sampleDao(): SampleDao

    companion object {
        @Volatile
        private var INSTANCE: CleanAndroidTemplateDatabase? = null

        /**
         * Gets the database instance using singleton pattern.
         * 
         * @param context Application context
         * @return Database instance
         */
        fun getDatabase(context: Context): CleanAndroidTemplateDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CleanAndroidTemplateDatabase::class.java,
                    "clean_android_template_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
