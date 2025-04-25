package com.example.prog7313ui.data

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
 */
@Database(
    entities = [User::class, Expense::class, BudgetCategory::class],
    version = 1,
    exportSchema = true)
    abstract class AppDatabase : RoomDatabase() {
        // Each DAO must be exposed as an abstract method
        abstract fun userDao(): UserDao
        abstract fun expenseDao(): ExpenseDao
        abstract fun budgetCategoryDao(): BudgetCategoryDao
    }
