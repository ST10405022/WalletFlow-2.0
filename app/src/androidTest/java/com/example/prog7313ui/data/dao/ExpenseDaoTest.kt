package com.example.prog7313ui.data.dao

import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.flow.first
import org.junit.Test
import org.junit.Assert.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.prog7313ui.data.AppDatabase
import com.example.prog7313ui.data.entity.Expense
import org.junit.*
import java.io.IOException
import java.util.*

/**
 * Unit tests for the ExpenseDao.
 * Uses Room.inMemoryDatabaseBuilder to create a temporary database for testing.
 * Ensures data access operations are correctly handled.
 * @see ExpenseDao
 * @reference (AndroidDevelopers, 2021).
 */
class ExpenseDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var expenseDao: ExpenseDao

    @Before
    fun setUp() {
        // Create an in-memory version of the database for testing purposes
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        expenseDao = db.expenseDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        // Close the database after each test to clean up
        db.close()
    }

    @Test
    fun insertExpense_andRetrieveAllExpenses_returnsInsertedItem() = runTest {
        // Arrange: Create a sample expense entity
        val expense = Expense(
            id = 1,
            amount = 150.0,
            date = Date(),
            startDate = null,
            endDate = null,
            description = "Test Grocery Expense",
            categoryId = 1,
            photoPath = null
        )

        // Act: Insert the expense and retrieve all expenses
        expenseDao.insertExpense(expense)
        val result = expenseDao.getAllExpenses().first()

        // Assert: The inserted expense is returned in the list
        assertEquals(1, result.size)
        assertEquals(expense.id, result[0].id)
        assertEquals("Test Grocery Expense", result[0].description)
    }

    @Test
    fun insertMultipleExpenses_retrieveExpensesByCategory_returnsFilteredList() = runTest {
        // Arrange: Create and insert multiple expenses
        val expenses = listOf(
            Expense(1, 100.0, Date(), null, null, "Food", categoryId = 1, photoPath = null),
            Expense(2, 200.0, Date(), null, null, "Transport", categoryId = 2, photoPath = null),
            Expense(3, 300.0, Date(), null, null, "Snacks", categoryId = 1, photoPath = null),
        )
        expenses.forEach { expenseDao.insertExpense(it) }

        // Act: Retrieve only expenses with categoryId = 1
        val categoryExpenses = expenseDao.getExpensesByCategory(1).first()

        // Assert: Only 2 expenses should match categoryId = 1
        assertEquals(2, categoryExpenses.size)
        assertTrue(categoryExpenses.all { it.categoryId == 1 })
    }

    @Test
    fun insertExpense_retrieveByDateRange_returnsCorrectItem() = runTest {
        // Arrange: Define fixed dates for filtering
        val now = Date()
        val yesterday = Date(now.time - 86400000L)
        val tomorrow = Date(now.time + 86400000L)

        // Insert a single expense with today’s date
        val expense = Expense(
            id = 1,
            amount = 500.0,
            date = now,
            startDate = null,
            endDate = null,
            description = "Electricity Bill",
            categoryId = 3,
            photoPath = null
        )
        expenseDao.insertExpense(expense)

        // Act: Retrieve expenses between yesterday and tomorrow
        val filtered = expenseDao.getExpensesBetweenDates(yesterday, tomorrow).first()

        // Assert: The inserted expense is returned
        assertEquals(1, filtered.size)
        assertEquals("Electricity Bill", filtered[0].description)
    }
}

/*
 * Reference List
 *     AndroidDevelopers, 2021. Kotlin coroutines and lifecycle. [Online]
 *     Available at: https://developer.android.com/topic/libraries/architecture/coroutines
 *     [Accessed 22 April 2025].
 *
 */