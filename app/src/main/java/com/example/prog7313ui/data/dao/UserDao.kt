package com.example.prog7313ui.data.dao

import androidx.room.*
import com.example.prog7313ui.data.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for interacting with the User table.
 * Room will auto-generate the implementation at build time.
 * @see User
 */
@Dao
interface UserDao {

    /**
     * Inserts a new user into the database.
     * If the user already exists, replaces the old record.
     * @param user The user to insert.
     * @return The ID of the newly inserted user.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    /**
     * Retrieves a user by their email from the database.
     * @param email The email of the user to retrieve.
     * @return The user with the specified email, or null if not found.
     */
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    /**
     * Retrieves a user by their ID from the database.
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID, or null if not found.
     */
    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Int): User?

    /**
     * Retrieves all users from the database.
     * @return A Flow emitting a list of all users.
     * The list is automatically updated when the database is changed.
     *
     */
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    /**
     * Updates a user in the database.
     * If the user does not exist, nothing happens.
     * @param user The user to update.
     */
    @Update
    suspend fun updateUser(user: User)

    /**
     * Deletes a user from the database.
     * If the user does not exist, nothing happens.
     * @param user The user to delete.
     */
    @Delete
    suspend fun deleteUser(user: User)
}
