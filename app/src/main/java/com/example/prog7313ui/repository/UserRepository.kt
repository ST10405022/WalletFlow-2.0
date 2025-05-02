package com.example.prog7313ui.repository

import com.example.prog7313ui.data.dao.UserDao
import com.example.prog7313ui.data.entity.User
//import com.example.prog7313ui.u
import java.security.MessageDigest
import com.example.prog7313ui.viewmodel.LoginResult
import kotlin.collections.joinToString
import kotlin.text.format
import kotlin.text.toByteArray


class UserRepository(private val userDao: UserDao) {

    object HashUtil {
        fun sha256(input: String): String {
            val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
            return bytes.joinToString("") { "%02x".format(it) }
        }
    }


    suspend fun registerUser(username: String,password: String): Boolean {
        val existing = userDao.getUserByUsername(username)
        if (existing != null) return false // user already exists
        val hashed = HashUtil.sha256(password)
        val newUser = User(
            username = username, password = password, hashedPassword = hashed,
            id = 0,
            name = "",
            surname = "",
            email = "$username@example.com"
        )
        userDao.insertUser(newUser)
        return true
    }


    suspend fun loginUser(username: String, password: String): LoginResult {
        val user = userDao.getUserByUsername(username)
        if (user == null) return LoginResult.UserNotFound

        val hashed = HashUtil.sha256(password)
        return if (user.hashedPassword == hashed) {
            LoginResult.Success
        } else {
            LoginResult.WrongPassword
        }
    }

    suspend fun updateUserPassword(username: String, newPassword: String): Boolean {
        val user = userDao.getUserByUsername(username) ?: return false
        val hashed = HashUtil.sha256(newPassword)
        return userDao.updatePassword(username, newPassword, hashed) > 0
    }

    suspend fun resetPassword(username: String, newPassword: String): Boolean {
        val user = userDao.getUserByUsername(username) ?: return false
        val hashed = UserRepository.HashUtil.sha256(newPassword)
        val updatedUser = user.copy(password = newPassword, hashedPassword = hashed)
        userDao.updateUser(updatedUser)
        return true
    }

}

//(Sekhon, 2020)
//(Tuto, 2023)
//(Tutorialspoint, 2024)

