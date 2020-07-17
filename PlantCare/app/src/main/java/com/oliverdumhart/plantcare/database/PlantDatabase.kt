package com.oliverdumhart.plantcare.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabasePlant::class], version = 1, exportSchema = false)
abstract class PlantDatabase : RoomDatabase() {
    abstract val plantDao: PlantDatabaseDao

    companion object {

        private lateinit var INSTANCE: PlantDatabase

        fun getInstance(context: Context): PlantDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PlantDatabase::class.java, "plant_database"
                    ).build()
                }

                return INSTANCE
            }
        }
    }
}