package com.example.prog7313ui.data.dao

import androidx.room.*
import com.example.prog7313ui.data.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for interacting with the User table.
 * Room will auto-generate the implementation at build time.
 */
@Dao
interface UserDao {

    // Inserts a new user into the database.
    // If the user already exists, replaces the old record.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    // Retrieves a user based on their email.
    // Returns null if no user is found.
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    // Retrieves a user by their unique ID.
    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Int): User?

    // Retrieves all users from the database as a stream of data.
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    // Updates an existing user's information.
    @Update
    suspend fun updateUser(user: User)

    // Deletes a specific user from the database.
    @Delete
    suspend fun deleteUser(user: User)
}
