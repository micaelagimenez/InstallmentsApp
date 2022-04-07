package com.project.debo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [InstallmentsData::class], version = 1, exportSchema = false)
abstract class InstallmentsDatabase : RoomDatabase() {

    abstract fun installmentsDao(): InstallmentsDao

    companion object {
        @Volatile
        private var INSTANCE: InstallmentsDatabase? = null

        fun getDatabase(context: Context): InstallmentsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InstallmentsDatabase::class.java,
                    "installment_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}