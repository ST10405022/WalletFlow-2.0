package com.example.prog7313ui.data.dao

import androidx.room.*
import com.example.prog7313ui.data.entity.BudgetCategory
import kotlinx.coroutines.flow.Flow

/**
 * DAO for interacting with the BudgetCategory table in the database.
 * Room will auto-generate the implementation at build time.
 * @see BudgetCategory
 */
@Dao
interface BudgetCategoryDao {
    /**
     * Inserts a new category into the database.
     * If the category already exists, replaces the old record.
     * @param category The category to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: BudgetCategory)

    /**
     * Updates a category in the database.
     * If the category does not exist, nothing happens.
     * @param category The category to update.
     */
    @Update
    suspend fun updateCategory(category: BudgetCategory)

    /**
     * Deletes a category from the database.
     * If the category does not exist, nothing happens.
     * @param category The category to delete.
     * @see BudgetCategory
     */
    @Delete
    suspend fun deleteCategory(category: BudgetCategory)

    /**
     * Retrieves all categories from the database.
     * The list is automatically updated when the database is changed.
     * Flow is used to handle asynchronous data streams.
     * @return A Flow emitting a list of all categories.
     */
    @Query("SELECT * FROM budget_categories")
    fun getAllCategories(): Flow<List<BudgetCategory>>

    @Query("SELECT * FROM budget_categories WHERE id = id")
    suspend fun getCategoryId(id: Int): BudgetCategory?
}
