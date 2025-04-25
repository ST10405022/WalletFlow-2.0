package com.example.prog7313ui.data.dao

import androidx.room.*
import com.example.prog7313ui.data.entity.BudgetCategory
import kotlinx.coroutines.flow.Flow

/**
 * DAO for interacting with the BudgetCategory table in the database.
 */
@Dao
interface BudgetCategoryDao {
    // Inserts a new category into the database.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: BudgetCategory)

    // Updates an existing category in the database.
    @Update
    suspend fun updateCategory(category: BudgetCategory)

    // Deletes a category from the database.
    @Delete
    suspend fun deleteCategory(category: BudgetCategory)

    // Retrieves all budget categories available to the user for display or processing.
    @Query("SELECT * FROM budget_categories")
    fun getAllCategories(): Flow<List<BudgetCategory>>

    // Retrieves a specific category by its name (e.g., used to validate expenses).
    @Query("SELECT * FROM budget_categories WHERE name = :name LIMIT 1")
    suspend fun getCategoryByName(name: String): BudgetCategory?
}
