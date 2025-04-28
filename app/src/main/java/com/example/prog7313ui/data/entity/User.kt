package com.example.prog7313ui.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a user of the budgeting app.
 * This class is marked as a Room @Entity, meaning Room will create a table for it in the database.
 */
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Auto-generated primary key for uniquely identifying each user
    val name: String,      // User's first name
    val surname: String,   // User's last name
    val username: String,  // Unique username for login
    val password: String,  // Password for login
    val email: String      // User's email address
)
