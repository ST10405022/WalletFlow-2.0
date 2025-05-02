package com.example.prog7313ui.data.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.prog7313ui.data.AppDatabase
import com.example.prog7313ui.data.entity.BudgetCategory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.io.IOException

class BudgetCategoryDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var categoryDao: BudgetCategoryDao

    @Before
    fun setup() {
        // Create an in-memory database instance for isolated test runs
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        // Initialize the DAO
        categoryDao = database.budgetCategoryDao()
    }

    @After
    @Throws(IOException::class)
    fun teardown() {
        // Close the database after each test
        database.close()
    }

    @Test
    fun insertCategory_andRetrieveById() = runTest {
        val category = BudgetCategory(id = 1, name = "Food")

        // Insert category into DB
        categoryDao.insertCategory(category)

        // Fetch by ID
        val result = categoryDao.getCategoryId(1)

        // Assert properties
        assertEquals("Food", result?.name)
        assertEquals(1, result?.id)
    }

    @Test
    fun updateCategory_shouldChangeName() = runTest {
        val category = BudgetCategory(id = 2, name = "Transport")
        categoryDao.insertCategory(category)

        // Modify name and update
        val updated = category.copy(name = "Travel")
        categoryDao.updateCategory(updated)

        // Fetch and verify
        val result = categoryDao.getCategoryId(2)
        assertEquals("Travel", result?.name)
    }

    @Test
    fun deleteCategory_shouldRemoveItFromDb() = runTest {
        val category = BudgetCategory(id = 3, name = "Shopping")
        categoryDao.insertCategory(category)

        // Delete it
        categoryDao.deleteCategory(category)

        // Should not be found
        val result = categoryDao.getCategoryId(3)
        assertNull(result)
    }

    @Test
    fun getAllCategories_shouldReturnAllInserted() = runTest {
        val cat1 = BudgetCategory(id = 4, name = "Groceries")
        val cat2 = BudgetCategory(id = 5, name = "Utilities")

        categoryDao.insertCategory(cat1)
        categoryDao.insertCategory(cat2)

        val all = categoryDao.getAllCategories().first()
        assertEquals(2, all.size)
        assertEquals("Groceries", all[0].name)
        assertEquals("Utilities", all[1].name)
    }
}
