package com.example.prog7313ui.data.dao

import androidx.room.*
import com.example.prog7313ui.data.entity.BudgetCategory
import kotlinx.coroutines.flow.Flow

/**
 * DAO for interacting with the BudgetCategory table in the database.
 * Room will auto-generate the implementation at build time.
 * @see BudgetCategory
 * @reference (AndroidDevelopers, 2021).
 */
@Dao
interface BudgetCategoryDao {
    /**
     * Inserts a new category into the database.
     * If the category already exists, replaces the old record.
     * @param category The category to insert.
     * @see BudgetCategory
     * @reference (AndroidDevelopers, 2021).
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: BudgetCategory)

    /**
     * Updates a category in the database.
     * If the category does not exist, nothing happens.
     * @param category The category to update.
     * @see BudgetCategory
     * @reference (AndroidDevelopers, 2021).
     */
    @Update
    suspend fun updateCategory(category: BudgetCategory)

    /**
     * Deletes a category from the database.
     * If the category does not exist, nothing happens.
     * @param category The category to delete.
     * @see BudgetCategory
     * @reference (AndroidDevelopers, 2021).
     */
    @Delete
    suspend fun deleteCategory(category: BudgetCategory)

    /**
     * Retrieves all categories from the database.
     * The list is automatically updated when the database is changed.
     * Flow is used to handle asynchronous data streams.
     * @return A Flow emitting a list of all categories.
     * @see BudgetCategory
     * @reference (AndroidDevelopers, 2021).
     */
    @Query("SELECT * FROM budget_categories")
    fun getAllCategories(): Flow<List<BudgetCategory>>

    /**
     * Retrieves a category by its ID from the database.
     * @param id The ID of the category to retrieve.
     * @return The category with the specified ID, or null if not found.
     * @see BudgetCategory
     * @reference (AndroidDevelopers, 2021).
     */
    @Query("SELECT * FROM budget_categories WHERE id = :id")
    suspend fun getCategoryId(id: Int): BudgetCategory?
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