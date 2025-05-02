package com.example.prog7313ui.data.dao

import androidx.room.*
import com.example.prog7313ui.data.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for interacting with the User table.
 * Room will auto-generate the implementation at build time.
 * @see User
 * @reference (AndroidDevelopers, 2021).
 */
@Dao
interface UserDao {

    /**
     * Inserts a new user into the database.
     * If the user already exists, replaces the old record.
     * @param user The user to insert.
     * @return The ID of the newly inserted user.
     * @see User
     * @reference (AndroidDevelopers, 2021).
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    /**
     * Retrieves a user by their email from the database.
     * @param email The email of the user to retrieve.
     * @return The user with the specified email, or null if not found.
     * @see User
     * @reference (AndroidDevelopers, 2021).
     */
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    /**
     * Retrieves a user by their ID from the database.
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID, or null if not found.
     * @see User
     * @reference (AndroidDevelopers, 2021).
     */
    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Int): User?

    /**
     * Retrieves all users from the database.
     * @return A Flow emitting a list of all users.
     * The list is automatically updated when the database is changed.
     * @see User
     * @reference (AndroidDevelopers, 2021).
     */
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    /**
     * Updates a user in the database.
     * If the user does not exist, nothing happens.
     * @param user The user to update.
     * @see User
     * @reference (AndroidDevelopers, 2021).
     */
    @Update
    suspend fun updateUser(user: User)

    /**
     * Deletes a user from the database.
     * If the user does not exist, nothing happens.
     * @param user The user to delete.
     * @see User
     * @reference (AndroidDevelopers, 2021).
     */
    @Delete
    suspend fun deleteUser(user: User)
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