package com.example.prog7313ui.data

import android.content.Context
import androidx.room.TypeConverters
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
 * @reference (AndroidDevelopers, 2021).
 */

val Migration012 = object : Migration(1, 2){
    override fun migrate(database: SupportSQLiteDatabase)
    {
        database.execSQL("ALTER TABLE budget_categories " +
                "ADD COLUMN imageUri TEXT")
    }
}

@Database(
    entities = [User::class, Expense::class, BudgetCategory::class],
    version = 2, // Increase version number when schema changes
    exportSchema = true) // Keep schema version history

@TypeConverters(Converters::class) // Use the custom converter to handle java.util.Date and Long

// AppDatabase is an abstract class that extends RoomDatabase and must be annotated with @Database (MikeT, 2022).
abstract class AppDatabase : RoomDatabase() {
    // Each DAO must be exposed as an abstract method (MikeT, 2022) (Tuto).
    abstract fun userDao(): UserDao // DAO for User entity
    abstract fun expenseDao(): ExpenseDao // DAO for Expense entity
    abstract fun budgetCategoryDao(): BudgetCategoryDao // DAO for BudgetCategory entity

    // Companion object to provide a singleton instance of the database (MikeT, 2022).
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null // Singleton instance

        /**
         * Returns a singleton instance of AppDatabase to prevent multiple DB connections.
         * @param context The application context.
         * @return The singleton instance of AppDatabase.
         * @reference (MikeT, 2022) (Tuto, 2023).
         */
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "walletflow_database"
                ).addMigrations(Migration012)
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

/*
 * Reference List
 *     AndroidDevelopers, 2021. Save data in a local database using Room. [Online]
 *     Available at: https://developer.android.com/training/data-storage/room
 *     [Accessed 22 April 2025].
 *     AndroidDevelopers, 2021. Kotlin coroutines and lifecycle. [Online]
 *     Available at: https://developer.android.com/topic/libraries/architecture/coroutines
 *     [Accessed 22 April 2025].
 *     AndroidDevelopers, 2021. CardView. [Online]
 *     Available at: https://developer.android.com/reference/androidx/cardview/widget/CardView
 *     [Accessed 25 April 2025].
 *     AndroidDevelopers, 2021. View binding. [Online]
 *     Available at: https://developer.android.com/topic/libraries/view-binding
 *     [Accessed 23 April 2025].
 *     AndroidDevelopers, 2021. AlertDialog. [Online]
 *     Available at: https://developer.android.com/reference/androidx/appcompat/app/AlertDialog
 *     [Accessed 24 April 2025].
 *     AndroidDevelopers, 2021. SimpleDateFormat. [Online]
 *     Available at: https://developer.android.com/reference/java/text/SimpleDateFormat
 *     [Accessed 23 April 2025].
 *     MikeT, 2022. stackOverflow. [Online]
 *     Available at: https://stackoverflow.com/questions/74477964/android-studio-add-a-database
 *     [Accessed 28 April 2025].
 *     Android. 2025. Create dynamic lists with RecyclerView:   views:   Android developers,
 *     Android Developers. [Online].
 *     Available at: https://developer.android.com/develop/ui/views/layout/recyclerview
 *     [Accessed: 15 April 2025].
 */