package com.example.prog7313ui.data.dao

import androidx.room.*
import com.example.prog7313ui.data.entity.Expense
import kotlinx.coroutines.flow.Flow
import java.util.Date

/**
 * Data Access Object (DAO) for performing CRUD operations on the Expense entity.
 * Room will auto-generate the implementation at build time.
 * @see Expense
 */
@Dao
interface ExpenseDao {
    /**
     * Inserts a new expense into the database.
     * If the expense already exists, replaces the old record.
     * @param expense The expense to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense)

    /**
     * Updates an expense in the database.
     * If the expense does not exist, nothing happens.
     * @param expense The expense to update.
     * @return The number of rows affected by the update operation.
     */
    @Update
    suspend fun updateExpense(expense: Expense)

    /**
     * Deletes an expense from the database.
     * If the expense does not exist, nothing happens.
     * @param expense The expense to delete.
     * @return The number of rows affected by the delete operation.
     */
    @Delete
    suspend fun deleteExpense(expense: Expense)

    /**
     * Retrieves all expenses from the database.
     * The list is automatically updated when the database is changed.
     * Flow is used to handle asynchronous data streams.
     * @return A Flow emitting a list of all expenses.
     */
    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<Expense>>

    /**
     * Retrieves all expenses associated with a specific category.
     * The list is automatically updated when the database is changed.
     * Flow is used to handle asynchronous data streams.
     * @param categoryId The ID of the category to filter expenses by.
     * @return A Flow emitting a list of expenses associated with the specified category.
     */
    @Query("SELECT * FROM expenses WHERE categoryId = :categoryId")
    fun getExpensesByCategory(categoryId: Int): Flow<List<Expense>>

    /**
     * Retrieves all expenses within a specific date range.
     * The list is automatically updated when the database is changed.
     * Flow is used to handle asynchronous data streams.
     * @param startDate The start of the date range.
     * @param endDate The end of the date range.
     * @return A Flow emitting a list of expenses within the specified date range.
     */
    @Query("""
        SELECT * FROM expenses 
        WHERE (:startDate IS NULL OR date >= :startDate)
          AND (:endDate IS NULL OR date <= :endDate)
        ORDER BY date DESC
    """)
    fun getExpensesBetweenDates(startDate: Date?, endDate: Date?): Flow<List<Expense>>
}
