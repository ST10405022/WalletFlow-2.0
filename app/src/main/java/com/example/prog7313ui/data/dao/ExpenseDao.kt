package com.example.prog7313ui.data.dao

import androidx.room.*
import com.example.prog7313ui.data.entity.Expense
import kotlinx.coroutines.flow.Flow
import java.util.Date

/**
 * Data Access Object (DAO) for performing CRUD operations on the Expense entity.
 * Room will auto-generate the implementation at build time.
 * @see Expense
 * @reference (AndroidDevelopers, 2021).
 */
@Dao
interface ExpenseDao {
    /**
     * Inserts a new expense into the database.
     * If the expense already exists, replaces the old record.
     * @param expense The expense to insert.
     * @return The ID of the newly inserted expense.
     * @see Expense
     * @reference (AndroidDevelopers, 2021).
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense)

    /**
     * Updates an expense in the database.
     * If the expense does not exist, nothing happens.
     * @param expense The expense to update.
     * @return The number of rows affected by the update operation.
     * @see Expense
     * @reference (AndroidDevelopers, 2021).
     */
    @Update
    suspend fun updateExpense(expense: Expense)

    /**
     * Deletes an expense from the database.
     * If the expense does not exist, nothing happens.
     * @param expense The expense to delete.
     * @return The number of rows affected by the delete operation.
     * @see Expense
     * @reference (AndroidDevelopers, 2021).
     */
    @Delete
    suspend fun deleteExpense(expense: Expense)

    /**
     * Retrieves all expenses from the database.
     * The list is automatically updated when the database is changed.
     * Flow is used to handle asynchronous data streams.
     * @return A Flow emitting a list of all expenses.
     * @see Expense
     * @reference (AndroidDevelopers, 2021).
     */
    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<Expense>>

    /**
     * Retrieves all expenses associated with a specific category.
     * The list is automatically updated when the database is changed.
     * Flow is used to handle asynchronous data streams.
     * @param categoryId The ID of the category to filter expenses by.
     * @return A Flow emitting a list of expenses associated with the specified category.
     * @see Expense
     * @reference (AndroidDevelopers, 2021).
     */
    @Query("SELECT * FROM expenses WHERE categoryId = :categoryId")
    fun getExpensesByCategory(categoryId: Int): Flow<List<Expense>>

    /**
     * Retrieves all expenses within a specific date range.
     * The list is automatically updated when the database is changed.
     * Flow is used to handle asynchronous data streams
     * @param startDate The start of the date range.
     * @param endDate The end of the date range.
     * @return A Flow emitting a list of expenses within the specified date range.
     * @see Expense
     * @see Date
     * @reference (AndroidDevelopers, 2021).
     */
    @Query("""
        SELECT * FROM expenses 
        WHERE (:startDate IS NULL OR date >= :startDate)
          AND (:endDate IS NULL OR date <= :endDate)
        ORDER BY date DESC
    """)
    fun getExpensesBetweenDates(startDate: Date?, endDate: Date?): Flow<List<Expense>>
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