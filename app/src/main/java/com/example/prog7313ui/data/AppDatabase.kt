package com.example.prog7313ui.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.prog7313ui.data.dao.UserDao
import com.example.prog7313ui.data.entity.User

/**
 * The main Room database class that connects all the entities and DAOs.
 * @Database annotation registers entities and version number.
 */
@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    // Each DAO must be exposed as an abstract method
    abstract fun userDao(): UserDao
}
