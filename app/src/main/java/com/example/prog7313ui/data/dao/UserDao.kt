package com.example.prog7313ui.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.prog7313ui.data.entity.User

/**
 * Data Access Object (DAO) interface for interacting with the User table.
 * Room will auto-generate the implementation at build time.
 */
@Dao
interface UserDao {

    /**
     * Insert a new user into the database.
     */
    @Insert
    suspend fun insertUser(user: User)

    /**
     * Fetch a user by username and password for login validation.
     */
    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    suspend fun loginUser(username: String, password: String): User?

    /**
     * Check if a username already exists (to prevent duplicates).
     */
    @Query("SELECT COUNT(*) FROM users WHERE username = :username")
    suspend fun isUsernameTaken(username: String): Int
}
