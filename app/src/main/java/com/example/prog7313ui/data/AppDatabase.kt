package com.example.prog7313ui.data

import android.content.Context
import androidx.room.TypeConverters
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.prog7313ui.data.dao.UserDao
import com.example.prog7313ui.data.entity.User
import com.example.prog7313ui.data.dao.ExpenseDao
import com.example.prog7313ui.data.entity.Expense
import com.example.prog7313ui.data.dao.BudgetCategoryDao
import com.example.prog7313ui.data.entity.BudgetCategory

/**
 * The main Room database class that connects all the entities and DAOs.
 * @Database annotation registers entities and version number.
 * @TypeConverters annotation registers the custom converter.
 * @Companion object provides a singleton instance of the database.
 * @getInstance method returns the singleton instance.
 */
@Database(
    entities = [User::class, Expense::class, BudgetCategory::class],
    version = 1, // Increase version number when schema changes
    exportSchema = true) // Keep schema version history

@TypeConverters(Converters::class) // Use the custom converter to handle java.util.Date and Long

// Abstract class for the database
abstract class AppDatabase : RoomDatabase() {
    // Each DAO must be exposed as an abstract method
    abstract fun userDao(): UserDao // DAO for User entity
    abstract fun expenseDao(): ExpenseDao // DAO for Expense entity
    abstract fun budgetCategoryDao(): BudgetCategoryDao // DAO for BudgetCategory entity

    // Companion object to provide a singleton instance of the database
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null // Singleton instance

        /**
         * Returns a singleton instance of AppDatabase to prevent multiple DB connections.
         */
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "walletflow_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
