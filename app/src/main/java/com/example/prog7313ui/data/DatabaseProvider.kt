package com.example.prog7313ui.data

import android.content.Context
import androidx.room.Room

/**
 * Singleton provider for the AppDatabase.
 * Ensures only one instance of the database is created across the app.
 */
object DatabaseProvider {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        // Double-checked locking to make sure only one instance is created
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "budget_db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
