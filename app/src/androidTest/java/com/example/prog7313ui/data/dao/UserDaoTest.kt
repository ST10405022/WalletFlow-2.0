package com.example.prog7313ui.data.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.prog7313ui.data.AppDatabase
import com.example.prog7313ui.data.entity.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for UserDao using an in-memory Room database.
 * Ensures correct functionality for CRUD operations.
 * @reference AndroidDevelopers, 2021
 */
@OptIn(ExperimentalCoroutinesApi::class)
class UserDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        userDao = db.userDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertAndGetUserByEmail_success() = runTest {
        val user = User(
            name = "Test",
            surname = "User",
            username = "TestUser",
            email = "test@example.com",
            password = "secret",
            hashedPassword = "secret")
        userDao.insertUser(user)

        val retrieved = userDao.getUserByEmail("test@example.com")
        assertNotNull(retrieved)
        assertEquals("TestUser", retrieved?.username)
        assertEquals("secret", retrieved?.password)
        assertEquals("secret", retrieved?.hashedPassword)
    }

    @Test
    fun insertUser_replacesExistingOnConflict() = runTest {
        val user1 = User(
            id = 1,
            name = "Conflict",
            surname = "User",
            username = "UserOne",
            email = "conflict@example.com",
            password = "first",
            hashedPassword = "first")
        val user2 = User(
            id = 1,
            name = "Conflict",
            surname = "UserTwo",
            username = "UserTwo",
            email = "conflict@example.com",
            password = "second",
            hashedPassword = "second")

        userDao.insertUser(user1)
        userDao.insertUser(user2)

        val result = userDao.getUserByEmail("conflict@example.com")
        assertNotNull(result)
        assertEquals("UserTwo", result?.username)
        assertEquals("second", result?.password)
        assertEquals("second", result?.hashedPassword)
    }

    @Test
    fun getUserById_returnsCorrectUser() = runTest {
        val user = User(
            name = "ById",
            surname = "User",
            username = "ById",
            email = "byid@example.com",
            password = "idpass",
            hashedPassword = "idpass")
        userDao.insertUser(user)

        val insertedUser = userDao.getUserByEmail("byid@example.com")
        assertNotNull(insertedUser)

        val resultById = userDao.getUserById(insertedUser!!.id)
        assertEquals(insertedUser.email, resultById?.email)
    }

    @Test
    fun updateUser_successfullyUpdatesUser() = runTest {
        val user = User(
            name = "Update",
            surname = "User",
            username = "OldName",
            email = "update@example.com",
            password = "123",
            hashedPassword = "123")
        userDao.insertUser(user)

        val insertedUser = userDao.getUserByEmail("update@example.com")!!
        val updatedUser = insertedUser.copy(username = "NewName", password = "456")
        userDao.updateUser(updatedUser)

        val result = userDao.getUserByEmail("update@example.com")
        assertEquals("NewName", result?.username)
        assertEquals("456", result?.password)
    }

    @Test
    fun deleteUser_removesUser() = runTest {
        val user = User(
            name = "Delete",
            surname = "User",
            username = "ToDelete",
            email = "delete@example.com",
            password = "del",
            hashedPassword = "del")
        userDao.insertUser(user)

        val insertedUser = userDao.getUserByEmail("delete@example.com")!!
        userDao.deleteUser(insertedUser)

        val result = userDao.getUserByEmail("delete@example.com")
        assertNull(result)
    }
}
