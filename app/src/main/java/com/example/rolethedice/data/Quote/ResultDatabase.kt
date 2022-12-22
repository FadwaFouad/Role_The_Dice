package com.example.rolethedice.data.Quote

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Result::class], version = 3)
abstract class ResultDatabase : RoomDatabase() {
    abstract fun resultDao(): ResultDao

    companion object {
        @Volatile
        private var INSTANCE: ResultDatabase? = null

        fun getDatabase(context: Context): ResultDatabase {
            if (INSTANCE == null)
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ResultDatabase::class.java,
                        "results.db"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            return INSTANCE!!
        }

    }
}
